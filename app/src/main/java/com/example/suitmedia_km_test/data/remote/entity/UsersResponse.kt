package com.example.suitmedia_km_test.data.remote


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class UsersResponse(
 @SerializedName("data")
 val `data`: List<Data>,
 @SerializedName("page")
 val page: Int,
 @SerializedName("per_page")
 val perPage: Int,
 @SerializedName("support")
 val support: Support,
 @SerializedName("total")
 val total: Int,
 @SerializedName("total_pages")
 val totalPages: Int
)

@Entity(tableName = "UsersTable")
@Parcelize
data class Data(
 @PrimaryKey
 @SerializedName("id")
 val id: Int,
 @SerializedName("avatar")
 val avatar: String,
 @SerializedName("email")
 val email: String,
 @SerializedName("first_name")
 val firstName: String,
 @SerializedName("last_name")
 val lastName: String
):Parcelable

data class Support(
 @SerializedName("text")
 val text: String,
 @SerializedName("url")
 val url: String
)