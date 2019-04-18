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

public class FloorButton extends Button implements Comparable<FloorButton>
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
    
    private int floorNumber = 0;
    // elapsedTimeButtonOn is in seconds. This gets set to 0 when buttonOn is set to false because elevator stopped at floor and is going
    // in correct direction for button.
    // Gets updated by buildingElevatorController.controller
    private int elapsedTimeButtonOn = 0;
    private int numberDispatchedElevator = 0; // If equal to 0 then no elevator has been dispatched. Once elevator gets to floor, it will be reset to 0
    private String panelType = Constant.FLOOR_PANEL_TYPE_PASSENGER;
    
    public FloorButton ()
    {
        this(Constant.FLOOR_PANEL_BTN_TYPE_UP, Constant.FLOOR_PANEL_TYPE_PASSENGER, 1); // u = up button, dw = down button
    }
    
    public FloorButton (String type, String panelType, int floorNumber)
    {
        this.buttonType = type;
        this.panelType = panelType;
        this.floorNumber = floorNumber;
    }

    public int getFloorNumber()
    {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber)
    {
        this.floorNumber = floorNumber;
    }

    public int getElapsedTimeButtonOn()
    {
        return elapsedTimeButtonOn;
    }

    public void setElapsedTimeButtonOn(int elapsedTimeButtonOn)
    {
        this.elapsedTimeButtonOn = elapsedTimeButtonOn;
    }

    public int getNumberDispatchedElevator()
    {
        return numberDispatchedElevator;
    }

    public void setNumberDispatchedElevator(int numberDispatchedElevator)
    {
        this.numberDispatchedElevator = numberDispatchedElevator;
    }

    public String getPanelType()
    {
        return panelType;
    }

    public void setPanelType(String panelType)
    {
        this.panelType = panelType;
    }

    public int compareTo(FloorButton compareFloorButton)
    {
        int compareElapsedTime = compareFloorButton.getElapsedTimeButtonOn();
        
        return compareElapsedTime - this.getElapsedTimeButtonOn();
    }
    
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

    }

}
