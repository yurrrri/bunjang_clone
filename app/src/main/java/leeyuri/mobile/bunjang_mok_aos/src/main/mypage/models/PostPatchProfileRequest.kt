package leeyuri.mobile.bunjang_mok_aos.src.main.mypage.models

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

data class PostPatchProfileRequest(
//    정보 각각을 변경하는것이기 때문에 모두 nullable
        @SerializedName("gender")
        @Nullable
        val gender: Char?,
        @SerializedName("birth")
        @Nullable
        val birth: String?,
        @SerializedName("phoneNumber")
        @Nullable
        val phoneNumber: String?,
        @SerializedName("isPhoneRelease")
        @Nullable
        val isPhoneRelease: Char?,
        @SerializedName("email")
        @Nullable
        val email: String?
)