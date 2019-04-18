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

public class ElevatorPanel extends Panel
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
    
    private ElevatorFloorButton [] elevatorFloorButtons;
    private ElevatorDoorButton doorOpenButton;
    private ElevatorDoorButton doorCloseButton;
    private int elevatorId = 0;
    private int numberOfFloors = 1;

    public ElevatorPanel()
    {
        // ep = elevator passenger panel, ef = elevator freight panel.
        // Normally, I'd make these values constants in a static class of constants or maybe in a config file
        this(Constant.ELEV_PANEL_TYPE_PASSENGER, 1, 6);
    }
    
    public ElevatorPanel(String type, int elevatorId, int numberFloors)
    {
        this.panelType = type;
        this.elevatorId = elevatorId;
        this.numberOfFloors = numberFloors;
        initElevatorPanel();
    }
    
    private void initElevatorPanel()
    {
        elevatorFloorButtons = new ElevatorFloorButton [numberOfFloors];
        
        for (int i = 0; i < elevatorFloorButtons.length; i++)
        {
            elevatorFloorButtons[i] = new ElevatorFloorButton(elevatorId, i+1);
        }
        doorOpenButton = new ElevatorDoorButton(Constant.ELEV_PANEL_DOOR_BTN_TYPE_OPEN, elevatorId);
        doorCloseButton = new ElevatorDoorButton(Constant.ELEV_PANEL_DOOR_BTN_TYPE_CLOSE, elevatorId);
    }
    public ElevatorFloorButton[] getElevatorFloorButtons()
    {
        return elevatorFloorButtons;
    }

    public void setElevatorFloorButtons(ElevatorFloorButton[] elevatorFloorButtons)
    {
        this.elevatorFloorButtons = elevatorFloorButtons;
    }

    public ElevatorDoorButton getDoorOpenButton()
    {
        return doorOpenButton;
    }

    public void setDoorOpenButton(ElevatorDoorButton doorOpenButton)
    {
        this.doorOpenButton = doorOpenButton;
    }

    public ElevatorDoorButton getDoorCloseButton()
    {
        return doorCloseButton;
    }

    public void setDoorCloseButton(ElevatorDoorButton doorCloseButton)
    {
        this.doorCloseButton = doorCloseButton;
    }

    public int getElevatorId()
    {
        return elevatorId;
    }

    public void setElevatorId(int elevatorId)
    {
        this.elevatorId = elevatorId;
    }

    public int getNumberOfFloors()
    {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors)
    {
        this.numberOfFloors = numberOfFloors;
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

    }

}
