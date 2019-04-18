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

public class Building
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
    private String [] elevatorTypes;
    private BuildingElevatorController elevatorController;
    private int controllerRunInterval = 1; //in seconds
    
    public Building(int numberOfElevators, String [] elevatorTypes, int numberOfFloors, int controllerRunInterval)
    {
        this.numberOfElevators = numberOfElevators;
        this.numberOfFloors = numberOfFloors;
        this.elevatorTypes = elevatorTypes;
        this.controllerRunInterval = controllerRunInterval;
        
        //TODO check if elevatorTypes.length is equal to numberOfElevators. If not, then return.
        
        elevators = new Elevator [numberOfElevators];
        floors = new Floor [numberOfFloors];
        
        for(int i=0; i < this.numberOfElevators; i++)
        {
            elevators[i] = new Elevator(elevatorTypes[i], i+1, this.numberOfFloors);
        }
        
        for(int i=0; i < this.numberOfFloors; i++)
        {
            floors[i] = new Floor(i+1, this.numberOfFloors);
        }
        
        elevatorController = new BuildingElevatorController(elevators, this.numberOfElevators, floors, this.numberOfFloors, this.controllerRunInterval);
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

    public String[] getElevatorTypes()
    {
        return elevatorTypes;
    }

    public void setElevatorTypes(String[] elevatorTypes)
    {
        this.elevatorTypes = elevatorTypes;
    }

    public BuildingElevatorController getElevatorController()
    {
        return elevatorController;
    }

    public void setElevatorController(BuildingElevatorController elevatorController)
    {
        this.elevatorController = elevatorController;
    }

    public int getControllerRunInterval()
    {
        return controllerRunInterval;
    }

    public void setControllerRunInterval(int controllerRunInterval)
    {
        this.controllerRunInterval = controllerRunInterval;
    }

    public static void main(String[] args)
    {
        int numberOfElevators = 4;
        int numberOfFloors = 6;
        int controllerRunInterval = 2; // seconds
        String [] elevatorTypes = {Constant.ELEV_TYPE_PASSENGER,Constant.ELEV_TYPE_PASSENGER,Constant.ELEV_TYPE_FREIGHT,Constant.ELEV_TYPE_FREIGHT};
        Building building = new Building( numberOfElevators, elevatorTypes, numberOfFloors, controllerRunInterval);
        
        building.elevatorController.controller();
        

    }

}
