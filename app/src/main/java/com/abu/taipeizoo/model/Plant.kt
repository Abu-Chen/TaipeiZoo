package com.abu.taipeizoo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
{
"F_Location": "臺灣動物區；蟲蟲探索谷；熱帶雨林區；鳥園；兩棲爬蟲動物館",
"F_Pic01_URL": "http://www.zoo.gov.tw/iTAP/04_Plant/Lythraceae/subcostata/subcostata_1.jpg",
"F_Name_Ch": "九芎",
"F_AlsoKnown": "苞飯花、拘那花、小果紫薇、南紫薇、猴不爬、怕癢樹、南紫薇、九荊、馬鈴花、蚊仔花",
"F_Name_En": "Subcostate Crape Myrtle",
"F_Brief": "分布於中低海拔森林及長江以南的地區，為台灣的原生樹種。主要生長在潮濕的崩塌地，有吸水保持土壤之特性，所以是良好的水土保持樹種。",
"F_Feature": "紅褐色的樹皮剝落後呈灰白色，樹幹光滑堅硬。葉有極短的柄，長橢圓形或卵形，全綠，葉片兩端尖，秋冬轉紅。夏季6～8月開花，花冠白色，花數甚多而密生於枝端，花為圓錐花序頂生，花瓣有長柄，邊緣皺曲像衣裙的花邊花絲長短不一。果實為蒴果，橢圓形約6-8公厘，種子有翅。",
"F_Function＆Application": "1. 優良薪炭材：木質堅硬耐燒，是臺灣優良的薪炭材之一。\n2. 水土保持植栽：可栽植於河岸及邊坡供水土保持。\n3. 農具用材：木質堅硬，乾燥後不太會反翹，是做農具的用材。\n4. 食用性：花、根入藥，味淡微苦，敗毒散瘀，花蕾味苦有清香，可生食。葉子是長尾水青蛾幼蟲的食草。",
"F_Update": "2017/9/4",
"_id": 1
}
 */
@Parcelize
data class Plant(
    @SerializedName("F_Location") val location: String,
    @SerializedName("F_Pic01_URL") val picUrl: String,
    @SerializedName("F_Name_Ch") val nameCh: String,
    @SerializedName("F_AlsoKnown") val alsoKnown: String,
    @SerializedName("F_Name_En") val nameEn: String,
    @SerializedName("F_Brief") val brief: String,
    @SerializedName("F_Feature") val feature: String,
    @SerializedName("F_Function＆Application") val function: String,
    @SerializedName("F_Update") val update: String,
    @SerializedName("_id") val id: Int,
) : Parcelable
