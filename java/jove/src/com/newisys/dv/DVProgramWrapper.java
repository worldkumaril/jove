/*
 * Jove - The Open Verification Environment for the Java (TM) Platform
 * Copyright (C) 2005 Newisys, Inc. or its licensors, as applicable.
 * Java is a registered trademark of Sun Microsystems, Inc. in the U.S. or
 * other countries.
 *
 * Licensed under the Open Software License version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You should
 * have received a copy of the License along with this software; if not, you
 * may obtain a copy of the License at
 *
 * http://opensource.org/licenses/osl-2.0.php
 *
 * This software is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.newisys.dv;

import com.newisys.eventsim.SimulationThread;
import com.newisys.eventsim.ThreadTerminatedException;

/**
 * Wraps DVApplication.run() to provide necessary simulator synchronization.
 * 
 * @author Trevor Robinson
 */
final class DVProgramWrapper
    implements Runnable
{
    private final DVSimulation dvSim;
    private final Runnable target;

    public DVProgramWrapper(DVSimulation dvSim, Runnable target)
    {
        this.dvSim = dvSim;
        this.target = target;
    }

    public void run()
    {
        try
        {
            // wait for read/write synch to run target
            dvSim.dvEventManager.waitForSynch();

            target.run();

            System.out.println("Program completed");

            // terminate and join all simulation threads
            final SimulationThread t = SimulationThread.currentThread();
            t.terminateChildren();
            t.joinChildren();

            // call $finish in the Verilog simulator
            dvSim.verilogSim.finish();
        }
        catch (ThreadTerminatedException e)
        {
            System.out.println("Program terminated");
        }
    }
}