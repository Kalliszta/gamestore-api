package com.qa.gamestore.domain;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderGames {
	//TO-DO implement once games in complete
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private Long fkOrderId;
	
	private Long fkGameId;
}
