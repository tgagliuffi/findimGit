
package com.bbva.findim.bck.domain.loan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbva.findim.bck.domain.communs.Bank;
import com.bbva.findim.bck.domain.communs.Branch;
import com.bbva.findim.bck.domain.creditApprovals.Qualification;
import com.bbva.findim.bck.domain.loanInput.Indicators;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "currency",
    "product",
    "conditions",
    "relatedContracts",
    "installmentTotalCount",
    "awardedAmount",
    "term",
    "requestDate",
    "interests",
    "expenses",
    "amortizationSchedule",
    "waitingPeriod",
    "paymentTotalAmount",
    "subsidizedTotalAmount",
    "interestTotalAmount",
    "expensesTotalAmount",
    "capitalPeriodicity",
    "interestPeriodicity",
    "commercialValueAmount",
    "nominalInstallmentAmount",
    "isMaximumQuotes"
})
public class Loan {

    @JsonProperty("currency")
    private Currency currency;
    @JsonProperty("product")
    private Product product;
    @JsonProperty("conditions")
    private Conditions conditions;
    @JsonProperty("relatedContracts")
    private List<RelatedContract> relatedContracts = null;
    @JsonProperty("installmentTotalCount")
    private Integer installmentTotalCount;
    @JsonProperty("awardedAmount")
    private AwardedAmount awardedAmount;
    @JsonProperty("term")
    private Term term;
    @JsonProperty("requestDate")
    private String requestDate;
    @JsonProperty("interests")
    private List<Interest> interests = null;
    @JsonProperty("expenses")
    private List<Expense> expenses = null;
    @JsonProperty("amortizationSchedule")
    private AmortizationSchedule amortizationSchedule;
    @JsonProperty("waitingPeriod")
    private WaitingPeriod waitingPeriod;
    @JsonProperty("paymentTotalAmount")
    private PaymentTotalAmount paymentTotalAmount;
    @JsonProperty("subsidizedTotalAmount")
    private SubsidizedTotalAmount subsidizedTotalAmount;
    @JsonProperty("interestTotalAmount")
    private InterestTotalAmount interestTotalAmount;
    @JsonProperty("expensesTotalAmount")
    private ExpensesTotalAmount expensesTotalAmount;
    @JsonProperty("capitalPeriodicity")
    private CapitalPeriodicity capitalPeriodicity;
    @JsonProperty("interestPeriodicity")
    private InterestPeriodicity interestPeriodicity;
    @JsonProperty("commercialValueAmount")
    private CommercialValueAmount commercialValueAmount;
    @JsonProperty("nominalInstallmentAmount")
    private NominalInstallmentAmount nominalInstallmentAmount;
    @JsonProperty("isMaximumQuotes")
    private Boolean isMaximumQuotes;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    @JsonProperty("id")
    private String id;
    @JsonProperty("signaturesRequiredNumber")
    private Integer signaturesRequiredNumber;
    @JsonProperty("dueDate")
    private String dueDate;
    @JsonProperty("pendingAmount")
    private PendingAmount pendingAmount;
    @JsonProperty("percentageProvision")
    private Integer percentageProvision;
    @JsonProperty("percentageWilling")
    private Integer percentageWilling;
    @JsonProperty("paymentDay")
    private Integer paymentDay;
    
    
    @JsonProperty("bank")
    private Bank bank;
    @JsonProperty("branch")
    private Branch branch;
    @JsonProperty("loanType")
    private LoanType loanType;
    @JsonProperty("loanManagementDates")
    private List<LoanManagementDate> loanManagementDates = null;
    @JsonProperty("partnership")
    private Partnership partnership;
    @JsonProperty("lifeCycle")
    private LifeCycle lifeCycle;
    @JsonProperty("delinquency")
    private Delinquency delinquency;
    @JsonProperty("qualification")
    private Qualification qualification;
    @JsonProperty("refinancingsNumber")
    private Integer refinancingsNumber;
    @JsonProperty("amortizationType")
    private AmortizationType amortizationType;
    @JsonProperty("indicators")
    private Indicators indicators;
    @JsonProperty("retainAmount")
    private RetainAmount retainAmount;
    @JsonProperty("pendingCapitalAmount")
    private PendingCapitalAmount pendingCapitalAmount;
    @JsonProperty("unexpiredCapitalAmount")
    private UnexpiredCapitalAmount unexpiredCapitalAmount;
    @JsonProperty("participants")
    private List<Participant> participants = null;



    public Currency getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @JsonProperty("product")
    public Product getProduct() {
        return product;
    }

    @JsonProperty("product")
    public void setProduct(Product product) {
        this.product = product;
    }

    @JsonProperty("conditions")
    public Conditions getConditions() {
        return conditions;
    }

    @JsonProperty("conditions")
    public void setConditions(Conditions conditions) {
        this.conditions = conditions;
    }

    @JsonProperty("relatedContracts")
    public List<RelatedContract> getRelatedContracts() {
        return relatedContracts;
    }

    @JsonProperty("relatedContracts")
    public void setRelatedContracts(List<RelatedContract> relatedContracts) {
        this.relatedContracts = relatedContracts;
    }

    @JsonProperty("installmentTotalCount")
    public Integer getInstallmentTotalCount() {
        return installmentTotalCount;
    }

    @JsonProperty("installmentTotalCount")
    public void setInstallmentTotalCount(Integer installmentTotalCount) {
        this.installmentTotalCount = installmentTotalCount;
    }

    @JsonProperty("awardedAmount")
    public AwardedAmount getAwardedAmount() {
        return awardedAmount;
    }

    @JsonProperty("awardedAmount")
    public void setAwardedAmount(AwardedAmount awardedAmount) {
        this.awardedAmount = awardedAmount;
    }

    @JsonProperty("term")
    public Term getTerm() {
        return term;
    }

    @JsonProperty("term")
    public void setTerm(Term term) {
        this.term = term;
    }

    @JsonProperty("requestDate")
    public String getRequestDate() {
        return requestDate;
    }

    @JsonProperty("requestDate")
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    @JsonProperty("interests")
    public List<Interest> getInterests() {
        return interests;
    }

    @JsonProperty("interests")
    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

    @JsonProperty("expenses")
    public List<Expense> getExpenses() {
        return expenses;
    }

    @JsonProperty("expenses")
    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @JsonProperty("amortizationSchedule")
    public AmortizationSchedule getAmortizationSchedule() {
        return amortizationSchedule;
    }

    @JsonProperty("amortizationSchedule")
    public void setAmortizationSchedule(AmortizationSchedule amortizationSchedule) {
        this.amortizationSchedule = amortizationSchedule;
    }

    @JsonProperty("waitingPeriod")
    public WaitingPeriod getWaitingPeriod() {
        return waitingPeriod;
    }

    @JsonProperty("waitingPeriod")
    public void setWaitingPeriod(WaitingPeriod waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    @JsonProperty("paymentTotalAmount")
    public PaymentTotalAmount getPaymentTotalAmount() {
        return paymentTotalAmount;
    }

    @JsonProperty("paymentTotalAmount")
    public void setPaymentTotalAmount(PaymentTotalAmount paymentTotalAmount) {
        this.paymentTotalAmount = paymentTotalAmount;
    }

    @JsonProperty("subsidizedTotalAmount")
    public SubsidizedTotalAmount getSubsidizedTotalAmount() {
        return subsidizedTotalAmount;
    }

    @JsonProperty("subsidizedTotalAmount")
    public void setSubsidizedTotalAmount(SubsidizedTotalAmount subsidizedTotalAmount) {
        this.subsidizedTotalAmount = subsidizedTotalAmount;
    }

    @JsonProperty("interestTotalAmount")
    public InterestTotalAmount getInterestTotalAmount() {
        return interestTotalAmount;
    }

    @JsonProperty("interestTotalAmount")
    public void setInterestTotalAmount(InterestTotalAmount interestTotalAmount) {
        this.interestTotalAmount = interestTotalAmount;
    }

    @JsonProperty("expensesTotalAmount")
    public ExpensesTotalAmount getExpensesTotalAmount() {
        return expensesTotalAmount;
    }

    @JsonProperty("expensesTotalAmount")
    public void setExpensesTotalAmount(ExpensesTotalAmount expensesTotalAmount) {
        this.expensesTotalAmount = expensesTotalAmount;
    }

    @JsonProperty("capitalPeriodicity")
    public CapitalPeriodicity getCapitalPeriodicity() {
        return capitalPeriodicity;
    }

    @JsonProperty("capitalPeriodicity")
    public void setCapitalPeriodicity(CapitalPeriodicity capitalPeriodicity) {
        this.capitalPeriodicity = capitalPeriodicity;
    }

    @JsonProperty("interestPeriodicity")
    public InterestPeriodicity getInterestPeriodicity() {
        return interestPeriodicity;
    }

    @JsonProperty("interestPeriodicity")
    public void setInterestPeriodicity(InterestPeriodicity interestPeriodicity) {
        this.interestPeriodicity = interestPeriodicity;
    }

    @JsonProperty("commercialValueAmount")
    public CommercialValueAmount getCommercialValueAmount() {
        return commercialValueAmount;
    }

    @JsonProperty("commercialValueAmount")
    public void setCommercialValueAmount(CommercialValueAmount commercialValueAmount) {
        this.commercialValueAmount = commercialValueAmount;
    }

    @JsonProperty("nominalInstallmentAmount")
    public NominalInstallmentAmount getNominalInstallmentAmount() {
        return nominalInstallmentAmount;
    }

    @JsonProperty("nominalInstallmentAmount")
    public void setNominalInstallmentAmount(NominalInstallmentAmount nominalInstallmentAmount) {
        this.nominalInstallmentAmount = nominalInstallmentAmount;
    }

    @JsonProperty("isMaximumQuotes")
    public Boolean getIsMaximumQuotes() {
        return isMaximumQuotes;
    }

    @JsonProperty("isMaximumQuotes")
    public void setIsMaximumQuotes(Boolean isMaximumQuotes) {
        this.isMaximumQuotes = isMaximumQuotes;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSignaturesRequiredNumber() {
		return signaturesRequiredNumber;
	}

	public void setSignaturesRequiredNumber(Integer signaturesRequiredNumber) {
		this.signaturesRequiredNumber = signaturesRequiredNumber;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public PendingAmount getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(PendingAmount pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public Integer getPercentageProvision() {
		return percentageProvision;
	}

	public void setPercentageProvision(Integer percentageProvision) {
		this.percentageProvision = percentageProvision;
	}

	public Integer getPercentageWilling() {
		return percentageWilling;
	}

	public void setPercentageWilling(Integer percentageWilling) {
		this.percentageWilling = percentageWilling;
	}

	public Integer getPaymentDay() {
		return paymentDay;
	}

	public void setPaymentDay(Integer paymentDay) {
		this.paymentDay = paymentDay;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public LoanType getLoanType() {
		return loanType;
	}

	public void setLoanType(LoanType loanType) {
		this.loanType = loanType;
	}

	public List<LoanManagementDate> getLoanManagementDates() {
		return loanManagementDates;
	}

	public void setLoanManagementDates(List<LoanManagementDate> loanManagementDates) {
		this.loanManagementDates = loanManagementDates;
	}

	public Partnership getPartnership() {
		return partnership;
	}

	public void setPartnership(Partnership partnership) {
		this.partnership = partnership;
	}

	public LifeCycle getLifeCycle() {
		return lifeCycle;
	}

	public void setLifeCycle(LifeCycle lifeCycle) {
		this.lifeCycle = lifeCycle;
	}

	public Delinquency getDelinquency() {
		return delinquency;
	}

	public void setDelinquency(Delinquency delinquency) {
		this.delinquency = delinquency;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public Integer getRefinancingsNumber() {
		return refinancingsNumber;
	}

	public void setRefinancingsNumber(Integer refinancingsNumber) {
		this.refinancingsNumber = refinancingsNumber;
	}

	public AmortizationType getAmortizationType() {
		return amortizationType;
	}

	public void setAmortizationType(AmortizationType amortizationType) {
		this.amortizationType = amortizationType;
	}

	public Indicators getIndicators() {
		return indicators;
	}

	public void setIndicators(Indicators indicators) {
		this.indicators = indicators;
	}

	public RetainAmount getRetainAmount() {
		return retainAmount;
	}

	public void setRetainAmount(RetainAmount retainAmount) {
		this.retainAmount = retainAmount;
	}

	public PendingCapitalAmount getPendingCapitalAmount() {
		return pendingCapitalAmount;
	}

	public void setPendingCapitalAmount(PendingCapitalAmount pendingCapitalAmount) {
		this.pendingCapitalAmount = pendingCapitalAmount;
	}

	public UnexpiredCapitalAmount getUnexpiredCapitalAmount() {
		return unexpiredCapitalAmount;
	}

	public void setUnexpiredCapitalAmount(UnexpiredCapitalAmount unexpiredCapitalAmount) {
		this.unexpiredCapitalAmount = unexpiredCapitalAmount;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	
	
    
}
