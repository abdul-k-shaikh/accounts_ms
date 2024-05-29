package com.eazybytes.accounts.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Accounts extends BaseEntity{
	
	
	@Column(name="customer_id")
	private Long customerId;
	
	@Column(name="account_Number")
	@Id
	private Long accountNumber;
	
	@Column(name="account_type")
	private String accountType;
	
	private String email;
	
	@Column(name="branch_Addrwaa")
	private String branchAddress;
}
