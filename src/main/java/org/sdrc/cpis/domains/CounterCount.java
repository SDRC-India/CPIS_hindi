package org.sdrc.cpis.domains;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="counter_count")

public class CounterCount implements Serializable {

		private static final long serialVersionUID = -2295952636358742934L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
		Integer id;
		
		@Column(name="no_Of_Counter")
		Integer noOfCounter;
		
		public Integer getNoOfCounter() {
			return noOfCounter;
		}

		public void setNoOfCounter(Integer noOfCounter) {
			this.noOfCounter = noOfCounter;
		}

		
		
		

}
