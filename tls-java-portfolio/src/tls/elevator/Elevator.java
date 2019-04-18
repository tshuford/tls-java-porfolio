/*
 * Copyright ©2019. Thomas L. Shuford Jr. All Rights Reserved.
 * 
 * PERMISSION
 * Permission to use, copy, modify, and redistribute this software for not-for-profit purposes is hereby granted, provided that the 
 * following conditions are met:
 * 
 *  1) Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *  2) Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer
 *     in the documentation and/or other materials provided with the distribution.
 *  3) The name of Thomas L. Shuford Jr. may not be used to endorse or promote products 
 *     derived from this software without specific prior written permission.
 * 
 * DISCLAIMER
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDER “AS IS” AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE 
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */

package tls.elevator;

public class Elevator
{
    private String copyright =
            "* Copyright ©2019. Thomas L. Shuford Jr. All Rights Reserved.\n"
            + "* \n"
            + "* PERMISSION\n"
            + "* Permission to use, copy, modify, and redistribute this software for not-for-profit purposes is hereby granted, provided that the\n"
            + "* following conditions are met:\n"
            + "* \n"
            + "*  1) Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.\n"
            + "*  2) Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer\n"
            + "*     in the documentation and/or other materials provided with the distribution.\n"
            + "*  3) The name of Thomas L. Shuford Jr. may not be used to endorse or promote products\n"
            + "*     derived from this software without specific prior written permission.\n"
            + "* \n" 
            + "* DISCLAIMER\n"
            + "* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDER “AS IS” AND ANY EXPRESS OR IMPLIED WARRANTIES, \n" 
            + "* INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE\n" 
            + "* DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,\n" 
            + "* SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR\n" 
            + "* SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, \n"
            + "* WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE\n" 
            + "* USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\n";
    
   private boolean inService = true;
    // elevatorMalfunction is set by the building elevator controller if it detects a serious malfunction. If elevatorMalfunction is set to true,
    // then inService is set to false.
    private boolean elevatorMalfunction = false;
    private boolean restrictedToFreight = false; // only used by building elevator controller if freightElevatorPanel is not null
    private WeightSensor weightSensor = null;
    private boolean isUnderWeightLimit = true;
    private int currentFloor = 1; // Initially all elevators will start on first floor
    private int currentDirection = Constant.ELEV_CURRENT_DIRECTION_IDLE; // down = -1, parked(idle no direction) = 0, up = 1
    // could have current direction, but be stopped at floor. If currentDirection is 0 (parked/idle) then inMotion will also be false
    // When currentDirection is set to idle or a different direction by the building elevator controller, the controller will also reset all panel elevator
    // floor buttons to off for the elevator.
    private boolean inMotion = false;
    // To determine if the elevator needs to stop at a floor; pickupFloors, passengerElevatorPanel, and freightElevatorPanel (if not null) need to
    // be queried.
    // pickupFloors is set by the building elevator controller based on the floor panel buttons on the floors and elevator direction and maybe on the fly
    // calculated distance to the floor (For example: closest elevator going in the correct direction and not past the floor gets the pickup).
    private PickupFloor [] pickupFloors; 
    private boolean pickupOnly = false;
    private int pickupOnlyDirection = Constant.ELEV_PICKUPONLY_DIRECTION_IDLE; //Idle means that the elevator is already at floor for pickupOnly
    // freight elevator will have both a passenger and freight elevator panel. Passenger elevator will only have the passenger panel.
    // freight panel will allow the building elevator controller to know which floors to open the back freight door on.
    private ElevatorPanel passengerElevatorPanel = null;
    private ElevatorPanel freightElevatorPanel = null; // if null after init, then not a freight elevator.
    // I'm assuming that the floor door is not a door (i.e. always closed if no elevator behind door) unless the elevator is stopped on the floor.
    // So, the state of the floor door becomes the state of the elevator door that has stopped on the floor and the floor door opens and then closes
    // with the elevator door.
    private ElevatorDoor passengerDoor = null;
    private ElevatorDoor freightDoor = null; // if null after init, then not a freight elevator
    // default p is passenger, f is freight. Normally I'd have the default in a class of constants or config file
    private String elevatorType = Constant.ELEV_TYPE_PASSENGER; 
    private int numberOfFloors = 0;
    private int elevatorId = 0;

    public Elevator ()
    {
        this(Constant.ELEV_TYPE_PASSENGER, 1, 6); // 6 = default number of floors. Normally, I'd have set these values up in a static class of constants or config file
    }
    public Elevator(String type, int elevatorId, int numberFloors)
    {
        this.elevatorType = type;
        this.elevatorId = elevatorId;
        this.numberOfFloors = numberFloors;
        initElevator(); 
    }
    
    private void initElevator ()
    {
        // initialize the elevator objects

        passengerElevatorPanel = new ElevatorPanel(Constant.ELEV_TYPE_PASSENGER, elevatorId, numberOfFloors);
        passengerDoor = new ElevatorDoor(Constant.ELEV_TYPE_PASSENGER, elevatorId);
        
        if (elevatorType == Constant.ELEV_TYPE_FREIGHT)
        {
            weightSensor = new WeightSensor(Constant.ELEV_FREIGHT_MAX_WEIGHT, elevatorId);
            // I'm assuming that every floor has a freight door for the freight elevator
            freightElevatorPanel = new ElevatorPanel(Constant.ELEV_TYPE_FREIGHT, elevatorId, numberOfFloors);
            freightDoor = new ElevatorDoor(Constant.ELEV_TYPE_FREIGHT, elevatorId);
        }
        else if (elevatorType == Constant.ELEV_TYPE_PASSENGER)
        {
            weightSensor = new WeightSensor(Constant.ELEV_PASSENGER_MAX_WEIGHT, elevatorId);
        }
        
        pickupFloors = new PickupFloor[numberOfFloors];
        
        for(int i=0; i < pickupFloors.length; i++)
        {
            pickupFloors[i] = new PickupFloor(i+1);
        }
    }

    public boolean isInService()
    {
        return inService;
    }
    public void setInService(boolean inService)
    {
        this.inService = inService;
    }
    public boolean isElevatorMalfunction()
    {
        return elevatorMalfunction;
    }
    public void setElevatorMalfunction(boolean elevatorMalfunction)
    {
        this.elevatorMalfunction = elevatorMalfunction;
    }
    public boolean isRestrictedToFreight()
    {
        return restrictedToFreight;
    }
    public void setRestrictedToFreight(boolean restrictedToFreight)
    {
        this.restrictedToFreight = restrictedToFreight;
    }
    public WeightSensor getWeightSensor()
    {
        return weightSensor;
    }
    public void setWeightSensor(WeightSensor weightSensor)
    {
        this.weightSensor = weightSensor;
    }
    public boolean isUnderWeightLimit()
    {
        return isUnderWeightLimit;
    }
    public void setUnderWeightLimit(boolean isUnderWeightLimit)
    {
        this.isUnderWeightLimit = isUnderWeightLimit;
    }
    public int getCurrentFloor()
    {
        return currentFloor;
    }
    public void setCurrentFloor(int currentFloor)
    {
        this.currentFloor = currentFloor;
    }
    public int getCurrentDirection()
    {
        return currentDirection;
    }
    public void setCurrentDirection(int currentDirection)
    {
        this.currentDirection = currentDirection;
    }
    public boolean isInMotion()
    {
        return inMotion;
    }
    public void setInMotion(boolean inMotion)
    {
        this.inMotion = inMotion;
    }
    public PickupFloor[] getPickupFloors()
    {
        return pickupFloors;
    }
    public void setPickupFloors(PickupFloor[] pickupFloors)
    {
        this.pickupFloors = pickupFloors;
    }
    public boolean isPickupOnly()
    {
        return pickupOnly;
    }
    public void setPickupOnly(boolean pickupOnly)
    {
        this.pickupOnly = pickupOnly;
    }
    public int getPickupOnlyDirection()
    {
        return pickupOnlyDirection;
    }
    public void setPickupOnlyDirection(int pickupOnlyDirection)
    {
        this.pickupOnlyDirection = pickupOnlyDirection;
    }
    public ElevatorPanel getPassengerElevatorPanel()
    {
        return passengerElevatorPanel;
    }
    public void setPassengerElevatorPanel(ElevatorPanel passengerElevatorPanel)
    {
        this.passengerElevatorPanel = passengerElevatorPanel;
    }
    public ElevatorPanel getFreightElevatorPanel()
    {
        return freightElevatorPanel;
    }
    public void setFreightElevatorPanel(ElevatorPanel freightElevatorPanel)
    {
        this.freightElevatorPanel = freightElevatorPanel;
    }
    public ElevatorDoor getPassengerDoor()
    {
        return passengerDoor;
    }
    public void setPassengerDoor(ElevatorDoor passengerDoor)
    {
        this.passengerDoor = passengerDoor;
    }
    public ElevatorDoor getFreightDoor()
    {
        return freightDoor;
    }
    public void setFreightDoor(ElevatorDoor freightDoor)
    {
        this.freightDoor = freightDoor;
    }
    public String getElevatorType()
    {
        return elevatorType;
    }
    public void setElevatorType(String elevatorType)
    {
        this.elevatorType = elevatorType;
    }
    public int getNumberOfFloors()
    {
        return numberOfFloors;
    }
    public void setNumberOfFloors(int numberOfFloors)
    {
        this.numberOfFloors = numberOfFloors;
    }
    public int getElevatorId()
    {
        return elevatorId;
    }
    public void setElevatorId(int elevatorId)
    {
        this.elevatorId = elevatorId;
    }
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

    }
}
