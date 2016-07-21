import java.util.List;
import java.util.Map;
import java.util.Queue;


public class ParkingLot {
	private final Queue<ParkingSpace> availableParkingSpaces;
	private final Map<LicensePlate, ParkingSpace> occupiedParkingSpaces;
	
	public void parkCar(Customer customer) {
		if(!customer.carToPark().isPresent()) {
			throw new RuntimeException("Customer doesn't have a car to park!");
		}
		
		ParkingSpace parkingSpace = this.availableParkingSpaces.poll();
		if(parkingSpace == null) {
			throw new RuntimeException("Parking lot is already full!");
		}
		
		parkingSpace.car = customer.currentCar;
		this.occupiedParkingSpaces.put(
				customer.currentCar.get().licensePlate,
				customer.currentCar);
		customer.currentCar = Optional.empty();
	}
	
	public void releaseCar(Customer customer) {
		Optional<Car> desiredCar = customer.desiredCar();
		if(!desiredCar.isPresent()) {
			throw new RuntimeException("No car to release specified by customer!");
		}
		
		if(customer.carToPark().isPresent()) {
			throw new RuntimeException("Customer already has a car!");
		}
		
		if(this.occupiedParkingSpaces.contains(desiredCar.get())) {
			if(desiredCar.get().getOwner().getDriversLicense() !=
					customer.getDriversLicense()) {
				throw new RuntimeException(
						"This customer doesn't own the car they are requesting!");
			}
			
			ParkingSpace parkingSpace = this.occupiedParkingSpaces.get(desiredCar.get());
			customer.currentCar = parkingSpace.car;
			parkingSpace.car = Optional.empty();
			this.availableParkingSpaces.add(parkingSpace);
		}
	}
	
	public static class ParkingSpace {
		private final int parkingSpaceID;
		private Optional<Car> car;
	}
	
	public static class Car {
		private final LicensePlate licensePlate;
		private final Customer owner;
	}
	
	public static class LicensePlate {
		
	}
	
	public static class Customer {
		private final DriversLicense driversLicense;
		private Optional<Car> currentCar;
		
		public Optional<Car> currentCar() {
			return this.currentCar;
		}
		
		public void setCurrentCar(Car car) {
			this.currentCar = car;
		}
		
		public Optional<Car> desiredCar() {
			if(this.currentCar.isPresent()) {
				throw new RuntimeException(
						"Customer cannot drive two cars at once.");
			}
			
			return null;
		}
	}
	
	public static class DriversLicense {
		
	}
}
