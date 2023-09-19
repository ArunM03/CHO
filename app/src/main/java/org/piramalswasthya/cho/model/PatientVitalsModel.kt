package org.piramalswasthya.cho.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass


@Entity(
    tableName = "PATIENT_VITALS",
    foreignKeys = [
        ForeignKey(
            entity = Patient::class,
            parentColumns = ["patientID"],
            childColumns = ["patientID"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = BenFlow::class,
            parentColumns = ["benFlowID"],
            childColumns = ["benFlowID"],
            onDelete = ForeignKey.NO_ACTION
        ),
    ]
)
@JsonClass(generateAdapter = true)
data class PatientVitalsModel (
    @PrimaryKey
    val vitalsId: String,
    @ColumnInfo(name = "height") val height: String?,
    @ColumnInfo(name = "weight") val weight: String?,
    @ColumnInfo(name = "bmi") val bmi: String?,
    @ColumnInfo(name = "waist_circumference") val waistCircumference: String?,
    @ColumnInfo(name = "temperature") val temperature: String?,
    @ColumnInfo(name = "pulse_rate") val pulseRate : String?,
    @ColumnInfo(name = "spo2") val spo2 : String?,
    @ColumnInfo(name = "bp_systolic") val bpSystolic : String?,
    @ColumnInfo(name = "bp_diastolic") val bpDiastolic : String?,
    @ColumnInfo(name = "respiratory_rate") val respiratoryRate : String?,
    @ColumnInfo(name = "rbs") val rbs: String?,
    @ColumnInfo(name = "patientID") val patientID: String,
    @ColumnInfo(name = "beneficiaryID") var beneficiaryID: Long? = null,
    @ColumnInfo(name = "beneficiaryRegID") var beneficiaryRegID: Long? = null,
    @ColumnInfo(name = "benFlowID") var benFlowID: Long? = null,
)