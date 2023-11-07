package org.piramalswasthya.cho.repositories

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.piramalswasthya.cho.database.room.dao.BenFlowDao
import org.piramalswasthya.cho.database.room.dao.CaseRecordeDao
import org.piramalswasthya.cho.model.AssociateAilmentsHistory
import org.piramalswasthya.cho.model.DiagnosisCaseRecord
import org.piramalswasthya.cho.model.InvestigationCaseRecord
import org.piramalswasthya.cho.model.InvestigationCaseRecordWithHigherHealthCenter
import org.piramalswasthya.cho.model.MedicationHistory
import org.piramalswasthya.cho.model.PatientDisplayWithVisitInfo
import org.piramalswasthya.cho.model.PrescriptionCaseRecord
import org.piramalswasthya.cho.model.PrescriptionWithItemMasterAndDrugFormMaster
import org.piramalswasthya.cho.model.VisitDB
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class CaseRecordeRepo @Inject constructor(
    private val caseRecordDao: CaseRecordeDao,
    private val benFlowDao: BenFlowDao,
) {
    suspend fun saveInvestigationToCatche(investigationCaseRecord: InvestigationCaseRecord) {
        try{
            withContext(Dispatchers.IO){
                caseRecordDao.insertInvestigationCaseRecord(investigationCaseRecord)
            }
        } catch (e: Exception){
            Timber.d("Error in saving Investigation $e")
        }
    }
    suspend fun saveDiagnosisToCatche(diagnosisCaseRecord: DiagnosisCaseRecord) {
        try{
            withContext(Dispatchers.IO){
                caseRecordDao.insertDiagnosisCaseRecord(diagnosisCaseRecord)
            }
        } catch (e: Exception){
            Timber.d("Error in saving Diagnosis $e")
        }
    }
    suspend fun getDiagnosisCaseRecordByPatientIDAndBenVisitNo(patientID: String, benVisitNo: Int) : List<DiagnosisCaseRecord>?{
        return caseRecordDao.getDiagnosisCaseRecordeByPatientIDAndBenVisitNo(patientID, benVisitNo)
    }
    suspend fun getInvestigationCaseRecordByPatientIDAndBenVisitNo(patientID: String, benVisitNo: Int) : InvestigationCaseRecordWithHigherHealthCenter?{
        return caseRecordDao.getInvestigationCaseRecordeByPatientIDAndBenVisitNo(patientID, benVisitNo)
    }
    suspend fun getPrescriptionCaseRecordeByPatientIDAndBenVisitNo(patientID: String, benVisitNo: Int) : List<PrescriptionWithItemMasterAndDrugFormMaster>?{
        return caseRecordDao.getPrescriptionCaseRecordeByPatientIDAndBenVisitNo(patientID, benVisitNo)
    }

    suspend fun savePrescriptionToCatche(prescriptionCaseRecord: PrescriptionCaseRecord) {
        try{
            withContext(Dispatchers.IO){
                caseRecordDao.insertPrescriptionCaseRecord(prescriptionCaseRecord)
            }
        } catch (e: Exception){
            Timber.d("Error in saving Prescription $e")
        }
    }
    fun getInvestigation(investigationId:String): LiveData<InvestigationCaseRecord> {
        return caseRecordDao.getInvestigationCasesRecordId(investigationId)
    }
    fun getPrescription(prescriptionId:String): LiveData<PrescriptionCaseRecord> {
        return caseRecordDao.getPrescriptionCasesRecordId(prescriptionId)
    }
    fun getDiagnosis(diagnosisId:String): LiveData<DiagnosisCaseRecord> {
        return caseRecordDao.getDiagnosisCasesRecordById(diagnosisId)
    }

    suspend fun getInvestigationCasesRecordByPatientIDAndVisitCodeAndBenFlowID(benVisitInfo: PatientDisplayWithVisitInfo): InvestigationCaseRecord? {
        val benFlow = benFlowDao.getBenFlowByBenRegIdAndBenVisitNo(benVisitInfo.patient.beneficiaryRegID!!, benVisitInfo.benVisitNo!!)
        var resp: InvestigationCaseRecord? = null
        if (benFlow != null) {
            resp = caseRecordDao.getPrescriptionCasesRecordByPatientIDAndVisitCodeAndBenFlowID(benVisitInfo.patient.patientID, benFlow.benVisitNo!!, benFlow.benFlowID)
        }
        return resp
    }

    suspend fun updateBenIdAndBenRegId(beneficiaryID: Long, beneficiaryRegID: Long, patientID: String){
//        caseRecordDao.updateBenIdBenRegIdPrescription(beneficiaryID, beneficiaryRegID, patientID)
//        caseRecordDao.updateBenIdBenRegIdInvestigation(beneficiaryID, beneficiaryRegID, patientID)
//        caseRecordDao.updateBenIdBenRegIdDiagnosis(beneficiaryID, beneficiaryRegID, patientID)
    }
    
}