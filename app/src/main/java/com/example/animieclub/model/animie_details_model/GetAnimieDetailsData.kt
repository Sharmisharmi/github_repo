package com.example.animieclub.model.animie_details_model

import com.example.animieclub.model.Aired
import com.example.animieclub.model.Broadcast
import com.example.animieclub.model.Demographic
import com.example.animieclub.model.Genre
import com.example.animieclub.model.Images
import com.example.animieclub.model.Licensor
import com.example.animieclub.model.Producer
import com.example.animieclub.model.Studio
import com.example.animieclub.model.Title
import com.example.animieclub.model.Trailer
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GetAnimieDetailsData {


    @SerializedName("mal_id")
    @Expose
     val malId: Int? = null

    @SerializedName("url")
    @Expose
     val url: String? = null

    @SerializedName("images")
    @Expose
     val images: ImagesDetailData? = null

    @SerializedName("trailer")
    @Expose
     val trailer: Trailer? = null

    @SerializedName("approved")
    @Expose
     val approved: Boolean? = null

    @SerializedName("titles")
    @Expose
     val titles: List<TitleDetailData>? = null

    @SerializedName("title")
    @Expose
     val title: String? = null

    @SerializedName("title_english")
    @Expose
     val titleEnglish: String? = null

    @SerializedName("title_japanese")
    @Expose
     val titleJapanese: String? = null

    @SerializedName("title_synonyms")
    @Expose
     val titleSynonyms: List<String>? = null

    @SerializedName("type")
    @Expose
     val type: String? = null

    @SerializedName("source")
    @Expose
     val source: String? = null

    @SerializedName("episodes")
    @Expose
     val episodes: Int? = null

    @SerializedName("status")
    @Expose
     val status: String? = null

    @SerializedName("airing")
    @Expose
     val airing: Boolean? = null

    @SerializedName("aired")
    @Expose
     val aired: AiredDetailData? = null

    @SerializedName("duration")
    @Expose
     val duration: String? = null

    @SerializedName("rating")
    @Expose
     val rating: String? = null

    @SerializedName("score")
    @Expose
     val score: Double? = null

    @SerializedName("scored_by")
    @Expose
     val scoredBy: Int? = null

    @SerializedName("rank")
    @Expose
     val rank: Int? = null

    @SerializedName("popularity")
    @Expose
     val popularity: Int? = null

    @SerializedName("members")
    @Expose
     val members: Int? = null

    @SerializedName("favorites")
    @Expose
     val favorites: Int? = null

    @SerializedName("synopsis")
    @Expose
     val synopsis: String? = null

    @SerializedName("background")
    @Expose
     val background: String? = null

    @SerializedName("season")
    @Expose
     val season: String? = null

    @SerializedName("year")
    @Expose
     val year: Int? = null

    @SerializedName("broadcast")
    @Expose
     val broadcast: BroadcastDetailData? = null

    @SerializedName("producers")
    @Expose
     val producers: List<Producer>? = null

    @SerializedName("licensors")
    @Expose
     val licensors: List<Licensor>? = null

    @SerializedName("studios")
    @Expose
     val studios: List<Studio>? = null

    @SerializedName("genres")
    @Expose
     val genres: List<Genre>? = null

    @SerializedName("explicit_genres")
    @Expose
     val explicitGenres: List<Any>? = null

    @SerializedName("themes")
    @Expose
     val themes: List<Any>? = null

    @SerializedName("demographics")
    @Expose
     val demographics: List<DemographicDetailData>? = null
}