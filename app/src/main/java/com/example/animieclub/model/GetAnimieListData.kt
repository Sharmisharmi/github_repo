package com.example.animieclub.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GetAnimieListData {


    @SerializedName("mal_id")
    @Expose
     var malId: Int? = null

    @SerializedName("url")
    @Expose
     var url: String? = null

    @SerializedName("images")
    @Expose
     var images: Images? = null

    @SerializedName("trailer")
    @Expose
     var trailer: Trailer? = null

    @SerializedName("approved")
    @Expose
     var approved: Boolean? = null

    @SerializedName("titles")
    @Expose
     var titles: List<Title>? = null

    @SerializedName("title")
    @Expose
     var title: String? = null

    @SerializedName("title_english")
    @Expose
     var titleEnglish: String? = null

    @SerializedName("title_japanese")
    @Expose
     var titleJapanese: String? = null

    @SerializedName("title_synonyms")
    @Expose
     var titleSynonyms: List<String>? = null

    @SerializedName("type")
    @Expose
     var type: String? = null

    @SerializedName("source")
    @Expose
     var source: String? = null

    @SerializedName("episodes")
    @Expose
     var episodes: Int? = null

    @SerializedName("status")
    @Expose
     var status: String? = null

    @SerializedName("airing")
    @Expose
     var airing: Boolean? = null

    @SerializedName("aired")
    @Expose
     var aired: Aired? = null

    @SerializedName("duration")
    @Expose
     var duration: String? = null

    @SerializedName("rating")
    @Expose
     var rating: String? = null

    @SerializedName("score")
    @Expose
     var score: Double? = null

    @SerializedName("scored_by")
    @Expose
     var scoredBy: Int? = null

    @SerializedName("rank")
    @Expose
     var rank: Int? = null

    @SerializedName("popularity")
    @Expose
     var popularity: Int? = null

    @SerializedName("members")
    @Expose
     var members: Int? = null

    @SerializedName("favorites")
    @Expose
     var favorites: Int? = null

    @SerializedName("synopsis")
    @Expose
     var synopsis: String? = null

    @SerializedName("background")
    @Expose
     var background: String? = null

    @SerializedName("season")
    @Expose
     var season: Any? = null

    @SerializedName("year")
    @Expose
     var year: Any? = null

    @SerializedName("broadcast")
    @Expose
     var broadcast: Broadcast? = null

    @SerializedName("producers")
    @Expose
     var producers: List<Producer>? = null

    @SerializedName("licensors")
    @Expose
     var licensors: List<Licensor>? = null

    @SerializedName("studios")
    @Expose
     var studios: List<Studio>? = null

    @SerializedName("genres")
    @Expose
     var genres: List<Genre>? = null

    @SerializedName("explicit_genres")
    @Expose
     var explicitGenres: List<Any>? = null

    @SerializedName("themes")
    @Expose
     var themes: List<Theme>? = null

    @SerializedName("demographics")
    @Expose
     var demographics: List<Demographic>? = null



}