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

import java.util.ArrayList;
import java.util.Collections;

public class BuildingElevatorController
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
    
    private int numberOfElevators = 1;
    private int numberOfFloors = 1;
    private Elevator [] elevators;
    private Floor [] floors;
    private int controllerRunInterval = 1; //in seconds
    private boolean controllerRunning = true; //If set to true controller will run. If set to false, controller will stop if it is currently running.
    private int loopTimeDuration = 60; // each loop in the controller method in seconds
    
    public BuildingElevatorController(Elevator [] elevators, int numberOfElevators, Floor [] floors, int numberOfFloors, int controllerRunInterval)
    {
        this.elevators = elevators;
        this.numberOfElevators = numberOfElevators;
        this.floors = floors;
        this.numberOfFloors = numberOfFloors;
        this.controllerRunInterval = controllerRunInterval;
    }
    
    private void setupTestState()
    {
        //Setup floors
        
        //floor 1
        floors[0].getPassengerPanel().getUpButton().setButtonOn(true);
        floors[0].getPassengerPanel().getUpButton().setElapsedTimeButtonOn(180);
        
        //floor 2
        floors[1].getPassengerPanel().getDownButton().setButtonOn(true);
        floors[1].getPassengerPanel().getDownButton().setElapsedTimeButtonOn(10);
        
        // Freight pickup will not get dispatched this time because least wait time and the the idle freight elevator is used for
        // floor 3 freight pickup with a longer wait time. The other freight elevator is going in the opposite direction
        floors[1].getFreightPanel().getDownButton().setButtonOn(true);
        floors[1].getFreightPanel().getDownButton().setElapsedTimeButtonOn(10);
        
        //floor 3
        floors[2].getPassengerPanel().getDownButton().setButtonOn(true);
        floors[2].getPassengerPanel().getDownButton().setNumberDispatchedElevator(2); //passenger only elevator
        floors[2].getPassengerPanel().getDownButton().setElapsedTimeButtonOn(90);
        
        floors[2].getFreightPanel().getUpButton().setButtonOn(true);
        floors[2].getFreightPanel().getUpButton().setElapsedTimeButtonOn(100);
        
        //floor 4
        
        //floor 5
        
        floors[4].getPassengerPanel().getUpButton().setButtonOn(true);
        floors[4].getPassengerPanel().getUpButton().setElapsedTimeButtonOn(90);
        
        floors[4].getFreightPanel().getUpButton().setButtonOn(true);
        floors[4].getFreightPanel().getUpButton().setElapsedTimeButtonOn(80);
        
        //floor 6
        
        
        //Elevator data
        
        //Elevator 1 passenger only
        elevators[0].setCurrentDirection(Constant.ELEV_CURRENT_DIRECTION_IDLE);
        elevators[0].setCurrentFloor(1);
        
        //Elevator 2 passenger only
        elevators[1].setCurrentDirection(Constant.ELEV_CURRENT_DIRECTION_DOWN);
        elevators[1].setCurrentFloor(4);
        elevators[1].setInMotion(true);
        elevators[1].getPassengerElevatorPanel().getElevatorFloorButtons()[2].setButtonOn(true); // passenger floor 3 button
        elevators[1].getPassengerElevatorPanel().getElevatorFloorButtons()[0].setButtonOn(true); // passenger floor 1 button
        
        //Elevator 3 freight and passenger
        elevators[2].setCurrentDirection(Constant.ELEV_CURRENT_DIRECTION_UP);
        elevators[2].setCurrentFloor(5);
        elevators[2].setInMotion(false);
        elevators[2].getPassengerElevatorPanel().getElevatorFloorButtons()[5].setButtonOn(true); // passenger floor 6 button
        elevators[2].getFreightElevatorPanel().getElevatorFloorButtons()[5].setButtonOn(true); // Freight floor 6 button
        
        //Elevator 4 freight and passenger
        
        elevators[3].setCurrentDirection(Constant.ELEV_CURRENT_DIRECTION_IDLE);
        elevators[3].setCurrentFloor(6);
        
    }
    
    private ArrayList<FloorButton> findFloorsNeedingDispatch()
    {
        ArrayList<FloorButton> floorButtons = new ArrayList<FloorButton>();
        
        for(int i = 0; i < numberOfFloors; i++)
        {
            
            if (floors[i].getPassengerPanel().getUpButton() != null
                    && floors[i].getPassengerPanel().getUpButton().isButtonOn()
                    && floors[i].getPassengerPanel().getUpButton().getNumberDispatchedElevator() == 0)
            {
                floorButtons.add(floors[i].getPassengerPanel().getUpButton());
            }
            
            if (floors[i].getPassengerPanel().getDownButton() != null
                    && floors[i].getPassengerPanel().getDownButton().isButtonOn()
                    && floors[i].getPassengerPanel().getDownButton().getNumberDispatchedElevator() == 0)
            {
                floorButtons.add(floors[i].getPassengerPanel().getDownButton());
            }
            
            if (floors[i].getFreightPanel().getUpButton() != null
                    && floors[i].getFreightPanel().getUpButton().isButtonOn()
                    && floors[i].getFreightPanel().getUpButton().getNumberDispatchedElevator() == 0)
            {
                floorButtons.add(floors[i].getFreightPanel().getUpButton());
            }
            
            if (floors[i].getFreightPanel().getDownButton() != null
                    && floors[i].getFreightPanel().getDownButton().isButtonOn()
                    && floors[i].getFreightPanel().getDownButton().getNumberDispatchedElevator() == 0)
            {
                floorButtons.add(floors[i].getFreightPanel().getDownButton());
            }
        }
        
        // sort floorButtons by descending order of elapsedTimeButtonOn
        Collections.sort(floorButtons);
        
        return floorButtons;
    }
    
    private Elevator evalElevator(FloorButton button, Elevator elevator, Elevator dispatchedElevator)
    {
        Elevator rtnElevator = dispatchedElevator;
        boolean elevatorCanDispatch = false;
        
        if (button != null && elevator != null)
        {
            int buttonFloor = button.getFloorNumber();
            String buttonType = button.getButtonType();
            int elevatorFloor = elevator.getCurrentFloor();
            int elevatorDirection = elevator.getCurrentDirection();
            int floorDistance = Math.abs(buttonFloor - elevatorFloor);
            boolean pickupOnly = elevator.isPickupOnly();
            
            if (buttonType.equalsIgnoreCase(Constant.FLOOR_PANEL_BTN_TYPE_UP))
            {
                if (elevatorDirection == Constant.ELEV_CURRENT_DIRECTION_UP && !pickupOnly)
                {
                    if (elevatorFloor <= buttonFloor)
                    {
                        elevatorCanDispatch = true;
                    }
                }
                else if (elevatorDirection == Constant.ELEV_CURRENT_DIRECTION_IDLE && !pickupOnly)
                {
                    elevatorCanDispatch = true;
                }
            }
            else if (buttonType.equalsIgnoreCase(Constant.FLOOR_PANEL_BTN_TYPE_DOWN))
            {
                if (elevatorDirection == Constant.ELEV_CURRENT_DIRECTION_DOWN && !pickupOnly)
                {
                    if (elevatorFloor >= buttonFloor)
                    {
                        elevatorCanDispatch = true;
                    }
                }
                else if (elevatorDirection == Constant.ELEV_CURRENT_DIRECTION_IDLE && !pickupOnly)
                {
                    elevatorCanDispatch = true;
                }
            }
            
            if (elevatorCanDispatch)
            {
                if (rtnElevator != null)
                {
                    // return the elevator that is closest to the floor.
                    int rtnElevatorFloorDistance = Math.abs(buttonFloor - rtnElevator.getCurrentFloor());
                    if (floorDistance < rtnElevatorFloorDistance)
                    {
                        rtnElevator = elevator;
                    }
                }
                else
                {
                    rtnElevator = elevator;
                }
            }
            
        }
        
        
        return rtnElevator;
    }
    
    private void dispatchElevators( ArrayList<FloorButton> floorButtons)
    {
        // floorButtons is sorted by descending order of elapsedTimeButtonOn
        
        for (FloorButton currentButton : floorButtons)
        {
            Elevator dispatchedElevator = null;
            for (Elevator elevator : elevators)
            {
                if (currentButton.getPanelType().equalsIgnoreCase(Constant.FLOOR_PANEL_TYPE_FREIGHT) && elevator.getElevatorType().equalsIgnoreCase(Constant.ELEV_TYPE_FREIGHT))
                {
                    dispatchedElevator = evalElevator(currentButton, elevator, dispatchedElevator);
                }
                else if (currentButton.getPanelType().equalsIgnoreCase(Constant.FLOOR_PANEL_TYPE_PASSENGER))
                {
                    dispatchedElevator = evalElevator(currentButton, elevator, dispatchedElevator);
                }
            }
            
            //TODO - if dispatchedElevator is not null, then dispatch it and update elevator and currentButton
            int buttonFloor = currentButton.getFloorNumber();
            String buttonType = currentButton.getButtonType();
            String buttonPanelType = currentButton.getPanelType();
            String panelType = currentButton.getPanelType();
            int elapsedWaitTime = currentButton.getElapsedTimeButtonOn();
            String pickupType = "";
            String going ="";
            
            if (buttonPanelType.equalsIgnoreCase(Constant.FLOOR_PANEL_TYPE_PASSENGER))
            {
                pickupType = "Passenger";
            }
            else
            {
                pickupType = "Freight";
            }
            
            if (buttonType.equalsIgnoreCase(Constant.FLOOR_PANEL_BTN_TYPE_UP))
            {
                going = "up";
            }
            else if (buttonType.equalsIgnoreCase(Constant.FLOOR_PANEL_BTN_TYPE_DOWN))
            {
                going = "down";
            }
            
            if (dispatchedElevator != null)
            {
                int elevatorFloor = dispatchedElevator.getCurrentFloor();
                int elevatorDirection = dispatchedElevator.getCurrentDirection();
                PickupFloor [] pickupFloor = dispatchedElevator.getPickupFloors();
                int elevatorId = dispatchedElevator.getElevatorId();
                int floorDistance = Math.abs(buttonFloor - elevatorFloor);
                int directionToFloor = buttonFloor - elevatorFloor;
                
                currentButton.setNumberDispatchedElevator(dispatchedElevator.getElevatorId());
                if (dispatchedElevator.getCurrentDirection() == Constant.ELEV_CURRENT_DIRECTION_IDLE)
                {
                    if (buttonType.equalsIgnoreCase(Constant.FLOOR_PANEL_BTN_TYPE_UP))
                    {
                        dispatchedElevator.setCurrentDirection(Constant.ELEV_CURRENT_DIRECTION_UP);
                    }
                    else if (buttonType.equalsIgnoreCase(Constant.FLOOR_PANEL_BTN_TYPE_DOWN))
                    {
                        dispatchedElevator.setCurrentDirection(Constant.ELEV_CURRENT_DIRECTION_DOWN);
                    }
                    if (buttonFloor != elevatorFloor)
                    {
                        dispatchedElevator.setPickupOnly(true);
                        if (directionToFloor > 0)
                        {
                            dispatchedElevator.setPickupOnlyDirection(Constant.ELEV_PICKUPONLY_DIRECTION_UP);
                        }
                        else if (directionToFloor < 0)
                        {
                            dispatchedElevator.setPickupOnlyDirection(Constant.ELEV_PICKUPONLY_DIRECTION_DOWN);
                        }
                    }
                }
                
                if (buttonPanelType.equalsIgnoreCase(Constant.FLOOR_PANEL_TYPE_PASSENGER))
                {
                    pickupFloor[buttonFloor -1].setPassengerPickup(true);
                }
                else
                {
                    pickupFloor[buttonFloor -1].setFreightPickup(true);
                }
                //TODO print dispatch info to console
                
                if (dispatchedElevator.isPickupOnly())
                {
                    System.out.println("Dispatched Elevator " + elevatorId +" to Floor " + buttonFloor +" for " + pickupType + " Pickup Only " 
                            + "going " + going + " - Elapsed Wait time of " + elapsedWaitTime + " seconds");
                }
                else
                {
                    System.out.println("Dispatched Elevator " + elevatorId +" to Floor " + buttonFloor +" for " + pickupType + " Pickup " 
                            + "going " + going + " - Elapsed Wait time of " + elapsedWaitTime + " seconds");
                }
            }
            else
            {
                // add loop time duration to button's elapsed time duration since it did not get dispatched
                currentButton.setElapsedTimeButtonOn(currentButton.getElapsedTimeButtonOn() + loopTimeDuration);
                System.out.println("No dispatchable Elevator for Floor " + buttonFloor +" for " + pickupType + " pickup "
                        + "going " + going + " - Elapsed Wait time of " + elapsedWaitTime + " seconds");
            }
            
        }
        
    }
    
    private void dispatch()
    {
        ArrayList<FloorButton> floorButtons;
        
        // find floors needing to be dispatched to
        floorButtons = findFloorsNeedingDispatch();
        
        // find and dispatch elevators for found floors buttons
        dispatchElevators(floorButtons);
        
        // scan each floor for non dispatched button
    }
    
    private void movePickupOnly(Elevator elevator)
    {
        int elevatorId = elevator.getElevatorId();
        int elevatorCurFloor = elevator.getCurrentFloor();
        int elevatorCurDirectn = elevator.getCurrentDirection();
        int elevatorPickupOnlyDirectn = elevator.getPickupOnlyDirection();
        PickupFloor [] pickupFloors;
        int pickupFloorDest = 0;
        boolean isPassengerPickup = false;
        boolean isFreightPickup = false;
        
        pickupFloors = elevator.getPickupFloors();
        for (PickupFloor pickupFloor : pickupFloors )
        {
            if (pickupFloor.isPassengerPickup() || pickupFloor.isFreightPickup())
            {
                pickupFloorDest = pickupFloor.getFloorNumber();
                if (pickupFloor.isPassengerPickup())
                {
                    isPassengerPickup = true;
                }
                else if (pickupFloor.isFreightPickup())
                {
                    isFreightPickup = true;
                }
                break;
            }
        }
        if (pickupFloorDest != 0)
        {
            if (pickupFloorDest == elevator.getCurrentFloor())
            {
                elevator.setPickupOnly(false);
                elevator.setInMotion(false);
            }
            else if (elevatorPickupOnlyDirectn == Constant.ELEV_PICKUPONLY_DIRECTION_UP)
            {
                elevator.setInMotion(true);
                elevator.setCurrentFloor(elevatorCurFloor + 1);
            }
            else if (elevatorPickupOnlyDirectn == Constant.ELEV_PICKUPONLY_DIRECTION_DOWN)
            {
                elevator.setInMotion(true);
                elevator.setCurrentFloor(elevatorCurFloor - 1);
            }
            
            if (elevator.isInMotion())
            {
                System.out.println("Elevator " + elevatorId + " moved to floor " + elevator.getCurrentFloor() + " from Floor " + elevatorCurFloor);
            }
            else
            {
                if (isPassengerPickup)
                {
                    System.out.println("Elevator " + elevatorId + " at Floor " + pickupFloorDest + " for Passenger Pickup Only");
                }
                if (isFreightPickup)
                {
                    System.out.println("Elevator " + elevatorId + " at Floor " + pickupFloorDest + " for Freight Pickup Only");
                }
                pickupFloors[pickupFloorDest -1].setFloorNumber(0);
                pickupFloors[pickupFloorDest -1].setPassengerPickup(false);
                pickupFloors[pickupFloorDest -1].setFreightPickup(false);
            }
        }
    }
    
    private void movePickupDropoff(Elevator elevator)
    {
        int elevatorId = elevator.getElevatorId();
        int elevatorCurFloor = elevator.getCurrentFloor();
        int elevatorCurDirectn = elevator.getCurrentDirection();
        int elevatorPickupOnlyDirectn = elevator.getPickupOnlyDirection();
        ElevatorFloorButton [] elevatorPassengerFloorButtons = elevator.getPassengerElevatorPanel().getElevatorFloorButtons();
        ElevatorFloorButton [] elevatorFreightFloorButtons = null;
        PickupFloor [] pickupFloors;
        int pickupFloorDest = 0;
        int dropoffFloorDest = 0;
        boolean isPickup = false;
        boolean isDropoff = false;
        
        if (elevator.getFreightElevatorPanel() != null)
        {
            elevatorFreightFloorButtons = elevator.getFreightElevatorPanel().getElevatorFloorButtons();
        }
        // next pickup and/or drop off floor
        
        pickupFloors = elevator.getPickupFloors();
                
        if (elevatorCurDirectn == Constant.ELEV_CURRENT_DIRECTION_UP)
        {
            //Next pickup floor
            for (int i=0; i < numberOfFloors; i++)
            {
                if (pickupFloors[i].isPassengerPickup() || pickupFloors[i].isFreightPickup())
                {
                    pickupFloorDest = pickupFloors[i].getFloorNumber();
                    break;
                }
            }
            //Next drop off floor
            for (int i=0; i < numberOfFloors; i++)
            {
                if (elevatorPassengerFloorButtons[i].isButtonOn())
                {
                    dropoffFloorDest = elevatorPassengerFloorButtons[i].getFloorNumber();
                    break;
                }
                else if (elevatorFreightFloorButtons != null && elevatorFreightFloorButtons[i].isButtonOn())
                {
                    dropoffFloorDest = elevatorFreightFloorButtons[i].getFloorNumber();
                    break;
                }
            }
        }
        else if (elevatorCurDirectn == Constant.ELEV_CURRENT_DIRECTION_DOWN)
        {
            //Next pickup floor
            for (int i=numberOfFloors-1; i >= 0; i--)
            {
                if (pickupFloors[i].isPassengerPickup() || pickupFloors[i].isFreightPickup())
                {
                    pickupFloorDest = pickupFloors[i].getFloorNumber();
                    break;
                }
            }
            //Next drop off floor
            for (int i=numberOfFloors-1; i >= 0; i--)
            {
                if (elevatorPassengerFloorButtons[i].isButtonOn())
                {
                    dropoffFloorDest = elevatorPassengerFloorButtons[i].getFloorNumber();
                    break;
                }
                else if (elevatorFreightFloorButtons != null && elevatorFreightFloorButtons[i].isButtonOn())
                {
                    dropoffFloorDest = elevatorFreightFloorButtons[i].getFloorNumber();
                    break;
                }
            }
        }
        
        // Pickup
        if (pickupFloorDest != 0)
        {
            if (pickupFloorDest == elevatorCurFloor)
            {
                isPickup = true;
            }
            else if (elevatorCurDirectn == Constant.ELEV_CURRENT_DIRECTION_UP)
            {
                isPickup = false;
            }
            else if (elevatorCurDirectn == Constant.ELEV_CURRENT_DIRECTION_DOWN)
            {
                isPickup = false;
            }
        }
        
        //Drop Off
        if (dropoffFloorDest != 0)
        {
            if (dropoffFloorDest == elevatorCurFloor)
            {
                isDropoff = true;
            }
            else if (elevatorCurDirectn == Constant.ELEV_CURRENT_DIRECTION_UP)
            {
                isDropoff = false;
            }
            else if (elevatorCurDirectn == Constant.ELEV_CURRENT_DIRECTION_DOWN)
            {
                isDropoff = false;
            }
        }
        
        if(isPickup || isDropoff)
        {
            elevator.setInMotion(false);
            
            if(isPickup)
            {
                System.out.println("Elevator " + elevatorId + " at Floor " + pickupFloorDest + " for Pickup");
                pickupFloors[pickupFloorDest -1].setPassengerPickup(false);
                pickupFloors[pickupFloorDest -1].setFreightPickup(false);
            }
            
            if(isDropoff)
            {
                if (elevatorPassengerFloorButtons[dropoffFloorDest - 1].isButtonOn())
                {
                    System.out.println("Elevator " + elevatorId + " at Floor " + dropoffFloorDest + " for Passenger Drop Off");
                    elevatorPassengerFloorButtons[dropoffFloorDest - 1].setButtonOn(false);
                }
                if (elevatorFreightFloorButtons != null && elevatorFreightFloorButtons[dropoffFloorDest - 1].isButtonOn())
                {
                    System.out.println("Elevator " + elevatorId + " at Floor " + dropoffFloorDest + " for Freight Drop Off");
                    elevatorFreightFloorButtons[dropoffFloorDest - 1].setButtonOn(false);
                }
            }
        }
        else
        {
            if (pickupFloorDest == 0 && dropoffFloorDest == 0)
            {
                elevator.setInMotion(false);
                elevator.setCurrentDirection(Constant.ELEV_CURRENT_DIRECTION_IDLE);
            }
            else if (elevatorCurDirectn == Constant.ELEV_CURRENT_DIRECTION_UP)
            {
                elevator.setInMotion(true);
                elevator.setCurrentFloor(elevatorCurFloor + 1);
            }
            else if (elevatorCurDirectn == Constant.ELEV_CURRENT_DIRECTION_DOWN)
            {
                elevator.setInMotion(true);
                elevator.setCurrentFloor(elevatorCurFloor - 1);
            }
            
            if (elevator.getCurrentFloor() != elevatorCurFloor)
            {
                System.out.println("Elevator " + elevatorId + " moved to floor " + elevator.getCurrentFloor() + " from Floor " + elevatorCurFloor);
            }
            
            if (elevator.getCurrentDirection() == Constant.ELEV_CURRENT_DIRECTION_IDLE && elevator.getCurrentFloor() == elevatorCurFloor)
            {
                System.out.println("Elevator " + elevatorId + " at floor " + elevatorCurFloor + " in Idle State");
            }

        }
        
    }
    
    private void move()
    {
        for (Elevator elevator : elevators)
        {
            int elevatorId = elevator.getElevatorId();
            int elevatorCurFloor = elevator.getCurrentFloor();
            int elevatorCurDirectn = elevator.getCurrentDirection();
            int elevatorPickupOnlyDirectn = elevator.getPickupOnlyDirection();
            PickupFloor [] pickupFloors;
            int pickupFloorDest = 0;
            
            pickupFloors = elevator.getPickupFloors();
            
            if (elevator.isPickupOnly())
            {
                movePickupOnly(elevator);
            }
            else
            {
                movePickupDropoff(elevator);
            }
        }
    }
    
    public void controller()
    {
        int maxInterationCount = 10;
        int iterationCount = 1;
        // TODO controller logic
        
        setupTestState();
        
        // controller will run every controllerRunInterval seconds
        // If I was going to have a complete elevator simulator, I'd probably run this in a separate thread.
        //I'd also create a singleton synchronized class named something like ControllerRunStatus that would
        // contain the runStatus from some other thread which would get the class lock when it needed to change
        // the current status of this controller class and then release it after it set it.
        
        controllerRunning = true;
        while (iterationCount <= maxInterationCount)
        {
            // sleep for controllerRunInterval number of seconds
            System.out.println("=============================");
            System.out.println("Interation # " + iterationCount);
            // manage elevators
            
            System.out.println("Dispatch Elevators");
            dispatch();
            System.out.println("-----------------------------");
            
            // move elevators
            
            System.out.println("Move Elevators");
            move();
            System.out.println("-----------------------------");
            
//            controllerRunning = false; // so while loop exits since this is not a functional simulation.
            
            // at the end of this loop it would get and wait for the lock on ControllerRunStatus to get the
            // changed run status and set it to controllerRunning. This would allow the controller method to
            // it's loop.
            
            iterationCount++;
        }
    }

    public int getNumberOfElevators()
    {
        return numberOfElevators;
    }

    public void setNumberOfElevators(int numberOfElevators)
    {
        this.numberOfElevators = numberOfElevators;
    }

    public int getNumberOfFloors()
    {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors)
    {
        this.numberOfFloors = numberOfFloors;
    }

    public Elevator[] getElevators()
    {
        return elevators;
    }

    public void setElevators(Elevator[] elevators)
    {
        this.elevators = elevators;
    }

    public Floor[] getFloors()
    {
        return floors;
    }

    public void setFloors(Floor[] floors)
    {
        this.floors = floors;
    }

    public int getControllerRunInterval()
    {
        return controllerRunInterval;
    }

    public void setControllerRunInterval(int controllerRunInterval)
    {
        this.controllerRunInterval = controllerRunInterval;
    }

    public boolean isControllerRunning()
    {
        return controllerRunning;
    }

    public void setControllerRunning(boolean controllerRunning)
    {
        this.controllerRunning = controllerRunning;
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

    }

}
