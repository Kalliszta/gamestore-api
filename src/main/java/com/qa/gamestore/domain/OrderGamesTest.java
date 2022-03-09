package com.qa.gamestore.domain;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderGamesTest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private Long fkOrderId;
	
	private Long fkGameId;
}
