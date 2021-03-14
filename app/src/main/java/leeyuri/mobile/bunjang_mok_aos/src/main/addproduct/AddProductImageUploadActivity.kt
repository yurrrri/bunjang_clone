package leeyuri.mobile.bunjang_mok_aos.src.main.addproduct

import android.Manifest
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.GridView
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.google.firebase.storage.FirebaseStorage
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.kakao.util.helper.FileUtils.getExternalStorageDirectory
import leeyuri.mobile.bunjang_mok_aos.R
import leeyuri.mobile.bunjang_mok_aos.config.BaseActivity
import leeyuri.mobile.bunjang_mok_aos.databinding.ActivityAddProductImageUploadBinding
import leeyuri.mobile.bunjang_mok_aos.src.main.addproduct.models.GalleryAdapter
import java.io.File
import java.io.IOException
import java.util.*
import java.util.Locale.getDefault


class AddProductImageUploadActivity : BaseActivity<ActivityAddProductImageUploadBinding>(
    ActivityAddProductImageUploadBinding::inflate
) {
    private val REQUEST_TAKE_PHOTO = 1
    private lateinit var currentPhotoPath: String
    private lateinit var picturelist : ArrayList<String> //사진리스트
    private lateinit var tofirebaselist : ArrayList<String> //파이어베이스에 넣을 리스트
    private lateinit var timeStamp:String
    private lateinit var imageFileName:String //현재 파일 이름

    private var permissionListener: PermissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            takePictureIntent()
        }

        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            showCustomToast("카메라 권한 접근을 허용하지 않으셨습니다")
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setSupportActionBar(binding.addproducttoolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = null

//        상품 액티비티에서 넘어온것인지 판단
        picturelist = if (intent.getStringArrayListExtra("picturelist")!=null){
            intent.getStringArrayListExtra("picturelist")!!
        } else{
            arrayListOf()
        }

        tofirebaselist = if (intent.getStringArrayListExtra("tofirebaselist")!=null) {
            intent.getStringArrayListExtra("tofirebaselist")!!
        }else{
            arrayListOf()
        }

//       상단의 체크버튼을 누르면 사진 리스트를 보내줌
        binding.addimageBtnToaddproduct.setOnClickListener {
            val intent = Intent(this, AddProductActivity::class.java)
            intent.putStringArrayListExtra("picturelist", picturelist)
            intent.putStringArrayListExtra("tofirebaselist", tofirebaselist)

            startActivity(intent)
            finish()
        }
//        갤러리 사진 가져오기
        getAllPhotos()

//        직접 찍기
        binding.addimageImgbtnPicture.setOnClickListener {
            //권한 체크
            TedPermission.with(applicationContext)
                .setPermissionListener(permissionListener)
                .setPermissions(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                )
                .check()
        }

//       갤러리 여러장 선택


    }

    // 사진 찍는 인텐트
    private fun takePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {

            val photoFile: File?
            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
                Log.e("captureCamera Error", ex.toString())
                return
            }
            // getUriForFile의 두 번째 인자는 Manifest provier의 authorites와 일치해야 함
            val providerURI = FileProvider.getUriForFile(this, packageName, photoFile)
            // 인텐트에 전달할 때는 FileProvier의 Return값인 content://로만!!, providerURI의 값에 카메라 데이터를 넣어 보냄
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, providerURI)
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
        }
    }

    // 사진 파일을 만드는 메소드
    @Throws(IOException::class)
    fun createImageFile(): File { // Create an image file name
        timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", getDefault()).format(Date())
        imageFileName = "JPEG_$timeStamp.jpg"
        val imageFile: File?
        val storageDir = File(
            getExternalStorageDirectory().toString() + "/Pictures",
            "번개장터_클론"
        )
        if (!storageDir.exists()) {
            Log.i("mCurrentPhotoPath1", storageDir.toString())
            storageDir.mkdirs()
        }
        imageFile = File(storageDir, imageFileName)
        currentPhotoPath = imageFile.absolutePath
        return imageFile
    }

    //갤러리에 저장 & 방금 찍은 사진 리스트에 저장
    private fun savePhoto() {
        //사진 폴더에 저장하기 위한 경로 선언
        val file = File(currentPhotoPath)
        val uri = Uri.fromFile(file)
        MediaScannerConnection.scanFile(this, arrayOf(file.toString()),
            null, null)
        picturelist.add(file.toUri().toString())

        val firebaseStorage= FirebaseStorage.getInstance()
        firebaseStorage.reference.child("번개장터_클론").child(imageFileName)
            .putFile(uri)

        tofirebaselist.add(imageFileName)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK){
//           사진저장 및 갤러리 다시 불러오기
            savePhoto()
            getAllPhotos()

//           상품등록 페이지로 넘어감
            val intent = Intent(this, AddProductActivity::class.java)
            intent.putStringArrayListExtra("picturelist", picturelist)
            intent.putStringArrayListExtra("tofirebaselist", tofirebaselist)
            startActivity(intent)
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onResume() {
        super.onResume()

//       갤러리 이미지 얻어오기 -> 다른 화면으로 돌아왔을때 사진 추가가 되면 갱신되어야하니까 갱신을 위해서 onResume에
        getAllPhotos()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.addproduct_toolbar_items, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addproduct_btn_complete -> {
//           상품등록 페이지로 넘어감
                val intent = Intent(this, AddProductActivity::class.java)
                intent.putStringArrayListExtra("picturedlist", picturelist)
                intent.putStringArrayListExtra("tofirebaselist", tofirebaselist)
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

//    전체사진 불러오기
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun getAllPhotos() {
        val uriExternal: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        Log.d("where is my photo", uriExternal.toString())
        var columnIndexID: Int
        var imageId: Long
        val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC")
        val uriArr = ArrayList<String>()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                // 사진 경로 Uri 가져오기
                columnIndexID = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                while (cursor.moveToNext()) {
                    imageId = cursor.getLong(columnIndexID)
                    val uriImage = Uri.withAppendedPath(uriExternal, "" + imageId)
                    uriArr.add(uriImage.toString())
                }
            }
            cursor.close()
            val adapter = GalleryAdapter(this, uriArr)
            binding.gallery.numColumns = 3 // 한 줄에 3개씩
            binding.gallery.adapter = adapter

            //       갤러리 사진 여러개 선택
            binding.gallery.choiceMode = GridView.CHOICE_MODE_MULTIPLE_MODAL
//            binding.gallery.setOnItemClickListener { parent, view, position, id ->
//
//            }

        }
    }
}