<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema">
	<xsl:template match="/">
		<queryCompoundEmployeeResponse>
			<xsl:for-each select="queryCompoundEmployeeResponse/CompoundEmployee">
				<!-- store CompoundEmployee in a variable -->
				<xsl:variable name="var_CompoundEmployee" select="./*" />
					<xsl:variable name="var_assignmentIdExternal" select="person/employment_information/assignmentIdExternal/text()" />
					<xsl:variable name="var_assignmentIdExternalPrevious" select="person/employment_information/assignmentIdExternal_previous/text()" />
					<xsl:variable name="var_firstName" select="person/personal_information/first_name/text()" />
					<xsl:variable name="var_firstNamePrevious" select="person/personal_information/first_name_previous/text()" />
					<xsl:variable name="var_firstNameAlt1" select="person/personal_information/first_name_alt1/text()" />
					<xsl:variable name="var_firstNameAlt1Previous" select="person/personal_information/first_name_alt1_previous/text()" />
					<xsl:variable name="var_lastName" select="person/personal_information/last_name/text()" />
					<xsl:variable name="var_lastNamePrevious" select="person/personal_information/last_name_previous/text()" />
					<xsl:variable name="var_lastNameAlt1" select="person/personal_information/last_name_alt1/text()" />
					<xsl:variable name="var_lastNameAlt1Previous" select="person/personal_information/last_name_alt1_previous/text()" />
					<xsl:variable name="var_costCenter" select="/person/employment_information/job_information/cost_center/text()" />
					<xsl:variable name="var_costCenterPrevious" select="/person/employment_information/job_information/cost_center_previous/text()" />
					<xsl:variable name="var_dateOfBirth" select="person/date_of_birth/text()" />
					<xsl:variable name="var_dateOfBirthPrevious" select="person/date_of_birth_previous/text()" />
					<xsl:variable name="var_gender" select="person/personal_information/gender/text()" />
					<xsl:variable name="var_genderPrevious" select="person/personal_information/gender_previous/text()" />
					<xsl:variable name="var_nationality" select="person/personal_information/nationality/text()" />
					<xsl:variable name="var_nationalityPrevious" select="person/personal_information/nationality_previous/text()" />
					<xsl:variable name="var_serviceDate" select="person/employment_information/serviceDate/text()" />
					<xsl:variable name="var_serviceDatePrevious" select="person/employment_information/serviceDate_previous/text()" />
					<xsl:variable name="var_startDate" select="person/employment_information/start_date/text()" />
					<xsl:variable name="var_startDatePrevious" select="person/employment_information/start_date_previous/text()" />
					<xsl:variable name="var_relUserID" select="person/employment_information/job_relation/user_id/text()" />
					<xsl:variable name="var_relUserIDPrevious" select="person/employment_information/job_relation/user_id_previous/text()" />
					<xsl:variable name="var_relationshipType" select="person/employment_information/job_relation/relationship_type/text()" />
					<xsl:variable name="var_relationshipTypePrevious" select="person/employment_information/job_relation/relationship_type_previous/text()" />
					<xsl:variable name="var_isFullTimeEmployee" select="person/employment_information/job_information/is_fulltime_employee/text()" />
					<xsl:variable name="var_isFullTimeEmployeePrevious" select="person/employment_information/job_information/is_fulltime_employee_previous/text()" />
					<xsl:variable name="var_department" select="person/employment_information/job_information/department/text()" />
					<xsl:variable name="var_departmentPrevious" select="person/employment_information/job_information/department_previous/text()" />
					<xsl:variable name="var_emplStatus" select="person/employment_information/job_information/emplStatus/text()" />
					<xsl:variable name="var_emplStatusPrevious" select="person/employment_information/job_information/emplStatus_previous/text()" />
					<xsl:variable name="var_employeeClass" select="person/employment_information/job_information/employee_class/text()" />
					<xsl:variable name="var_employeeClassPrevious" select="person/employment_information/job_information/employee_class_previous/text()" />
					<xsl:variable name="var_contractEndDate" select="person/employment_information/job_information/contract_end_date/text()" />
					<xsl:variable name="var_contractEndDatePrevious" select="person/employment_information/job_information/contract_end_date_previous/text()" />
					<xsl:variable name="var_nationalId" select="person/national_id_card/national_id/text()" />
					<xsl:variable name="var_nationalIdPrevious" select="person/national_id_card/national_id_previous/text()" />
					<xsl:variable name="var_emailType" select="person/email_information/email_type/text()" />
					<xsl:variable name="var_emailTypePrevious" select="person/email_information/email_type_previous/text()" />
					<xsl:variable name="var_emailAddress" select="person/email_information/email_address/text()" />
					<xsl:variable name="var_emailAddressPrevious" select="person/email_information/email_address_previous/text()" />
					<xsl:variable name="var_accountNumber" select="person/employment_information/PaymentInformationV3/PaymentInformationDetailV3/accountNumber/text()" />
					<xsl:variable name="var_accountNumberPrevious" select="person/employment_information/PaymentInformationV3/PaymentInformationDetailV3/accountNumber_previous/text()" />
					<xsl:variable name="var_accountOwner" select="person/employment_information/PaymentInformationV3/PaymentInformationDetailV3/accountOwner/text()" />
					<xsl:variable name="var_accountOwnerPrevious" select="person/employment_information/PaymentInformationV3/PaymentInformationDetailV3/accountOwner_previous/text()" />
					<xsl:variable name="var_bank" select="person/employment_information/PaymentInformationV3/PaymentInformationDetailV3/bank/text()" />
					<xsl:variable name="var_bankPrevious" select="person/employment_information/PaymentInformationV3/PaymentInformationDetailV3/bank_previous/text()" />
					<xsl:variable name="var_iban" select="person/employment_information/PaymentInformationV3/PaymentInformationDetailV3/iban/text()" />
					<xsl:variable name="var_ibanPrevious" select="person/employment_information/PaymentInformationV3/PaymentInformationDetailV3/iban_previous/text()" />
					<xsl:variable name="var_retirementStatus" select="person/employment_information/benefits_eligibility_start_date/text()" />
					<xsl:variable name="var_retirementStatusPrevious" select="person/employment_information/benefits_eligibility_start_date_previous/text()" />
					<xsl:variable name="var_payCompValue" select="person/employment_information/compensation_information/paycompensation_recurring/paycompvalue/text()" />
					<xsl:variable name="var_payCompValuePrevious" select="person/employment_information/compensation_information/paycompensation_recurring/paycompvalue_previous/text()" />
					<xsl:variable name="var_currencyCode" select="person/employment_information/compensation_information/paycompensation_recurring/currency_code/text()" />
					<xsl:variable name="var_currencyCodePrevious" select="person/employment_information/compensation_information/paycompensation_recurring/currency_code_previous/text()" />
					<xsl:variable name="var_frequency" select="person/employment_information/compensation_information/paycompensation_recurring/frequency/text()" />
					<xsl:variable name="var_frequencyPrevious" select="person/employment_information/compensation_information/paycompensation_recurring/frequency_previous/text()" />
					<xsl:variable name="var_isRehire" select="person/employment_information/job_information/event/text()" />
					<xsl:variable name="var_isRehirePrevious" select="person/employment_information/job_information/event_previous/text()" />
					<xsl:variable name="var_serviceDate" select="person/employment_information/serviceDate/text()" />
					<xsl:variable name="var_serviceDatePrevious" select="person/employment_information/serviceDate_previous/text()" />
					
					<xsl:if test= "$var_assignmentIdExternal != $var_assignmentIdExternalPrevious or $var_firstName != $var_firstNamePrevious or $var_firstNameAlt1 != $var_firstNameAlt1Previous or $var_lastName != $var_lastNamePrevious or $var_lastNameAlt1 != $var_lastNameAlt1Previous or $var_costCenter != $var_costCenterPrevious or $var_dateOfBirth != $var_dateOfBirthPrevious or $var_gender != $var_genderPrevious or $var_nationality != $var_nationalityPrevious or $var_serviceDate != $var_serviceDatePrevious or $var_startDate != $var_startDatePrevious or $var_relUserID != $var_relUserIDPrevious or $var_relationshipType != $var_relationshipTypePrevious or $var_isFullTimeEmployee != $var_isFullTimeEmployeePrevious or $var_department != $var_departmentPrevious or $var_emplStatus != $var_emplStatusPrevious or $var_employeeClass != $var_employeeClassPrevious or $var_contractEndDate != $var_contractEndDatePrevious or $var_nationalId != $var_nationalIdPrevious or $var_emailType != $var_emailTypePrevious or $var_emailAddress != $var_emailAddressPrevious or $var_accountNumber != $var_accountNumberPrevious or $var_accountOwner != $var_accountOwnerPrevious or $var_bank != $var_bankPrevious or $var_iban != $var_ibanPrevious or $var_retirementStatus != $var_retirementStatusPrevious or $var_payCompValue != $var_payCompValuePrevious or $var_currencyCode != $var_currencyCodePrevious or $var_frequency != $var_frequencyPrevious or $var_isRehire != $var_isRehirePrevious or $var_serviceDate != $var_serviceDatePrevious">
						<CompoundEmployee>
						<xsl:copy-of select="$var_CompoundEmployee" />
						</CompoundEmployee>
						<!-- <xsl:text>I</xsl:text> -->
					</xsl:if>					
						<!--<xsl:text>O</xsl:text> -->
						<!-- Ignore all other employees  -->
			</xsl:for-each>
		</queryCompoundEmployeeResponse>
	</xsl:template>
</xsl:stylesheet>