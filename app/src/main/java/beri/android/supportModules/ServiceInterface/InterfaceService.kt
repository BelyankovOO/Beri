package beri.android.supportModules.ServiceInterface

import beri.android.supportModules.DataService.*
import beri.android.supportModules.Model.Image
import beri.android.supportModules.Model.Subcategory
import beri.android.supportModules.Model.User
import retrofit2.Call
import retrofit2.http.*

interface InterfaceService{

    //UserApi

    @GET("api/users/me")
    fun getUser(): Call<User>

    //CategoryApi

    @GET("api/categories")
    fun getCategories(): Call<List<CategoryRequest>>

    //AuthorizationApi

    @POST("api/auth/identify")
    fun getMessage(@Body identifyResponse: IdentifyResponse): Call<IdentifyRequest>

    @POST("api/auth/authorize")
    fun getTokens(@Body authorizeResponse: AuthorizeResponse): Call<AuthorizeRequest>

    @POST("api/auth/refresh")
    fun refreshTokens(@Body refreshResponce: RefreshResponce): Call<AuthorizeRequest>

    //ImageApi

    @GET("api/images/{imageId}")
    fun getImagesById(@Path("imageId") id: String): Call<Image>

    //SubcategoryApi

    @GET("api/subcategories?")
    fun getSubcategories(@Query("categoryId" )categoryId: String): Call<List<Subcategory>>

}