package com.razan.weatherkotlin.model

import java.io.Serializable

data class Country (
    val name: String,
    val topLevelDomain: List<String>,
    val alpha2Code: String,
    val alpha3Code: String,
    val callingCodes: List<String>,
    val capital: String,
    val altSpellings: List<String>,
    val region: String,
    val subregion: String,
    val population: Long,
    val latlng: List<Float>,
    val demonym: String?,
    val area: Double,
    val gini: Double,
    val timezones: List<String>,
    val borders: List<String>,
    val nativeName: String,
    val numericCode: String,
    val currencies: List<String>,
    val languages: List<String>,
    val translations: Translations,
    val relevance: String): Serializable

