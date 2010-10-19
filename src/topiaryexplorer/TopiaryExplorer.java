package topiaryexplorer;

/*
Topiary Explorer - tree viewer/data explorer for phylogenetic trees and associated data
Copyright (C) 2009  University of Colorado

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/

//package net.sourceforge.topiarytool;
 
import javax.swing.*;

/**
 * TopiaryExplorer is the main class for the Topiary Explorer application.
 * It takes care of creating the main JFrame and displaying the GUI.
 */

public class TopiaryExplorer {
    /**
     * Create the main GUI and display it on screen.
     */
     public static void createAndShowGUI() {
        //create the main window
        JFrame frame = new MainFrame();
        frame.pack();
        frame.setVisible(true);
     }
     
     public static void main(String[] args) {
        createAndShowGUI();
     }

}