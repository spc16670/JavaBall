package n.models;

public class Referee implements Comparable<Referee> {
	
	public static final int[] SELECTED_REFEREES = {0,1};
	public static final String LIST_RECORD_FORMAT = "%3s %12s %12s %5s %3s %8s %3s" ;
	public static final String FILE_RECORD_FORMAT = "%5s %15s %15s %15s %15s %15s %15s" ;
	public static final String FIELD_ID = "ID";
	public static final String FIELD_FIRST_NAME = "FIRST NAME";
	public static final String FIELD_LAST_NAME = "LAST NAME";
	public static final String FIELD_QUALIFICATION = "QUALIFICATION";
	public static final String FIELD_ALLOCATIONS = "ALLOCATIONS";
	public static final String FIELD_HOME_AREA = "HOME AREA";
	public static final String FIELD_TRAVEL_AREA = "TRAVEL AREA";
	public static final String[] FIELD_NAMES = {FIELD_ID,FIELD_FIRST_NAME
		,FIELD_LAST_NAME,FIELD_QUALIFICATION,FIELD_ALLOCATIONS,FIELD_HOME_AREA
		,FIELD_TRAVEL_AREA};
	
	String id;
	String firstName;
	String lastName;
	Qualification qualification;
	int allocations;
	Area homeArea;
	TravelAreas travelAreas;
	
	public Referee(String firstName,String lastName) {
		this.id = "";
		this.firstName = firstName;
		this.lastName = lastName;
		this.qualification = new Qualification("");
		this.allocations = 0;
		this.homeArea = new North(true);
		this.travelAreas = new TravelAreas(this.homeArea);
	}
	
	public Referee(String id,String firstName,String lastName
			,Qualification qualification,int allocations
			,Area homeArea,TravelAreas travelAreas) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.qualification = qualification;
		this.allocations = allocations;
		this.homeArea = homeArea;
		this.travelAreas = travelAreas;
	}

	public Referee(Referee clone) {
		this.id = clone.getId();
		this.firstName = clone.getFirstName();
		this.lastName = clone.getLastName();
		this.qualification = clone.getQualification();
		this.allocations = clone.getAllocations();
		this.homeArea = clone.getHomeArea();
		this.travelAreas = clone.getTravelAreas();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public int getAllocations() {
		return allocations;
	}

	public void setAllocations(int allocations) {
		this.allocations = allocations;
	}

	public Area getHomeArea() {
		return homeArea;
	}
	
	public void increaseAllocations() {
		this.allocations++;
	}
	
	public void decreaseAllocations() {
		this.allocations--;
	}
	
	public void setHomeArea(Area homeArea) {
		this.homeArea = homeArea;
		if (homeArea instanceof North) {
			this.getTravelAreas().getNorth().setTravel(true);
		} else if (homeArea instanceof Central) {
			this.getTravelAreas().getCentral().setTravel(true);
		} else if (homeArea instanceof South) {
			this.getTravelAreas().getSouth().setTravel(true); 	
		}
	}

	public TravelAreas getTravelAreas() {
		return travelAreas;
	}

	public void setTravelAreas(TravelAreas travelAreas) {
		this.travelAreas = travelAreas;
	}

	public String printRefereeRecord() {
		return "Id: " + this.getId() 
				+ "\nName: " + this.getFirstName() + " " + this.getLastName()
				+ "\nArea: " + this.getHomeArea()
				+ "\nQualification: " + this.getQualification().toString()
				+ "\nAllocations: " + this.getAllocations()
				+ "\nTravels: " + this.getTravelAreas().toString();
	}
	
	public String printRefereeRecord(String format) {
		return String.format(format, this.getId()
				,this.getFirstName(),this.getLastName()
				,this.getQualification().toString()
				,Integer.toString(this.getAllocations())
				,this.getHomeArea().toString()
				,this.getTravelAreas().toString());
	}
		
	@Override
	public String toString() {
		return this.getFirstName() + " " + this.getLastName();
	}

	public boolean idMatch(Referee other) {
		return this.getId().equals(other.getId()) ? true : false;
	}

	/*
	 * Sorting should be done on first two characters of the ID
	 * and then the number
	 */
	@Override
	public int compareTo(Referee other) {
		String thisInitials = this.getId().substring(0,2);
		String otherInitials = other.getId().substring(0,2);
		int thisIdNum = Integer.parseInt(this.getId().substring(2,3));
		int otherIdNum = Integer.parseInt(other.getId().substring(2,3));
		int areEqual = thisInitials.compareTo(otherInitials);
		if (areEqual == 0) {
			return thisIdNum == otherIdNum ? 0 : thisIdNum < otherIdNum ? -1 : 1; 
		} else {
			return areEqual;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + allocations;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((homeArea == null) ? 0 : homeArea.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((qualification == null) ? 0 : qualification.hashCode());
		result = prime * result
				+ ((travelAreas == null) ? 0 : travelAreas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Referee other = (Referee) obj;
		if (allocations != other.allocations)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (homeArea == null) {
			if (other.homeArea != null)
				return false;
		} else if (!homeArea.equals(other.homeArea))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (qualification == null) {
			if (other.qualification != null)
				return false;
		} else if (!qualification.equals(other.qualification))
			return false;
		if (travelAreas == null) {
			if (other.travelAreas != null)
				return false;
		} else if (!travelAreas.equals(other.travelAreas))
			return false;
		return true;
	}
	
}
