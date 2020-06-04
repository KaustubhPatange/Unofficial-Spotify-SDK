package com.kpstv.spotifyapi.data.models


import com.google.gson.annotations.SerializedName

data class AudioAnalysis(
    val bars: List<Bar>,
    val beats: List<Beat>,
    val meta: Meta,
    val sections: List<Section>,
    val segments: List<Segment>,
    val tatums: List<Tatum>,
    val track: Track
) {
    data class Bar(
        val confidence: Double,
        val duration: Double,
        val start: Double
    )

    data class Beat(
        val confidence: Double,
        val duration: Double,
        val start: Double
    )

    data class Meta(
        @SerializedName("analysis_time")
        val analysisTime: Double,
        @SerializedName("analyzer_version")
        val analyzerVersion: String,
        @SerializedName("detailed_status")
        val detailedStatus: String,
        @SerializedName("input_process")
        val inputProcess: String,
        val platform: String,
        @SerializedName("status_code")
        val statusCode: Int,
        val timestamp: Int
    )

    data class Section(
        val confidence: Double,
        val duration: Double,
        val key: Int,
        @SerializedName("key_confidence")
        val keyConfidence: Double,
        val loudness: Double,
        val mode: Int,
        @SerializedName("mode_confidence")
        val modeConfidence: Double,
        val start: Double,
        val tempo: Double,
        @SerializedName("tempo_confidence")
        val tempoConfidence: Double,
        @SerializedName("time_signature")
        val timeSignature: Int,
        @SerializedName("time_signature_confidence")
        val timeSignatureConfidence: Double
    )

    data class Segment(
        val confidence: Double,
        val duration: Double,
        @SerializedName("loudness_end")
        val loudnessEnd: Double,
        @SerializedName("loudness_max")
        val loudnessMax: Double,
        @SerializedName("loudness_max_time")
        val loudnessMaxTime: Double,
        @SerializedName("loudness_start")
        val loudnessStart: Double,
        val pitches: List<Double>,
        val start: Double,
        val timbre: List<Double>
    )

    data class Tatum(
        val confidence: Double,
        val duration: Double,
        val start: Double
    )

    data class Track(
        @SerializedName("analysis_channels")
        val analysisChannels: Int,
        @SerializedName("analysis_sample_rate")
        val analysisSampleRate: Double,
        @SerializedName("code_version")
        val codeVersion: Double,
        val codestring: String,
        val duration: Double,
        @SerializedName("echoprint_version")
        val echoprintVersion: Double,
        val echoprintstring: String,
        @SerializedName("end_of_fade_in")
        val endOfFadeIn: Double,
        val key: Int,
        @SerializedName("key_confidence")
        val keyConfidence: Double,
        val loudness: Double,
        val mode: Int,
        @SerializedName("mode_confidence")
        val modeConfidence: Double,
        @SerializedName("offset_seconds")
        val offsetSeconds: Int,
        @SerializedName("rhythm_version")
        val rhythmVersion: Int,
        val rhythmstring: String,
        @SerializedName("sample_md5")
        val sampleMd5: String,
        @SerializedName("start_of_fade_out")
        val startOfFadeOut: Double,
        @SerializedName("synch_version")
        val synchVersion: Int,
        val synchstring: String,
        val tempo: Double,
        @SerializedName("tempo_confidence")
        val tempoConfidence: Double,
        @SerializedName("time_signature")
        val timeSignature: Int,
        @SerializedName("time_signature_confidence")
        val timeSignatureConfidence: Double,
        @SerializedName("window_seconds")
        val windowSeconds: Int
    )
}