package main;

import model.*;
import controller.*;
import tui.*;

public class TryMe {
	ProductController prodCon;
	EmployeeController empCon;
	
	public TryMe (){
		prodCon.createSellableProduct("Magnuses gamle sko", "1111", "De lugter lidt", 200d, 10);
		empCon.createEmployee("10101010", "hej@hejsa.dk", "Mike", 4321, PersonRole.manager);
		
	}
}
