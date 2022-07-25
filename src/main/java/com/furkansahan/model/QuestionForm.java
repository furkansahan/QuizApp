package com.furkansahan.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class QuestionForm {

	private List<Question> questions;
	

}
