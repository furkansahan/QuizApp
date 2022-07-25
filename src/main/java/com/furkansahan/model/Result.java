package com.furkansahan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "results")
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "user_name")
	private String username;
	@Column(name = "total_correct")
	private double totalCorrect = 0;
	@Column(name = "satisfaction")
	private String satisfaction;
	@Column(name = "message")
	private String message;

}
