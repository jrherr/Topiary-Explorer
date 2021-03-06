package topiaryexplorer;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.event.*;


public class VerticalTreeToolbar extends JToolBar {

    JButton zoomOutButton  = new JButton("-");
    JButton zoomInButton = new JButton("+");
    JSlider zoomSlider = new JSlider(JSlider.VERTICAL, 1, 5000, 1);
    JPanel holder = new JPanel();
    JPanel spacer1 = new JPanel();
    double minYScale = 0;

    TreeWindow frame = null;

    public VerticalTreeToolbar(TreeWindow _frame) {

        super(JToolBar.VERTICAL);
        this.setLayout(new BorderLayout());
/*        holder.setLayout(new BoxLayout(holder, BoxLayout.Y_AXIS));*/
        holder.setLayout(new BorderLayout());
        
        frame = _frame;
        
        zoomOutButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                if(frame.zoomLocked)
                {
                    frame.zoomOut();
                    return;
                }
                zoomSlider.setValue(zoomSlider.getValue() - 100);
                syncTreeWithZoomSlider();
            }

        });
        zoomInButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                if(frame.zoomLocked)
                {
                    frame.zoomIn();
                    return;
                }
                zoomSlider.setValue(zoomSlider.getValue() + 100);
                syncTreeWithZoomSlider();
            }

        });
        holder.add(zoomInButton, BorderLayout.NORTH);
        // zoomSlider.setSnapToTicks(true);
        zoomSlider.setPreferredSize(new Dimension(20,300));
        zoomSlider.setMaximumSize(new Dimension(20,300));
        zoomSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (zoomSlider.getValueIsAdjusting()){
                    syncTreeWithZoomSlider();
                }
                frame.tree.redraw();
            }
        });

        holder.add(zoomSlider, BorderLayout.CENTER);
        holder.add(zoomOutButton, BorderLayout.SOUTH);
        add(holder, BorderLayout.SOUTH);
        setFloatable(false);
    }
    
        public void sliderEnabled(boolean b) {
        zoomSlider.setEnabled(b);
    }

    public void setValue(int i) {
        zoomSlider.setValue(i);
        syncTreeWithZoomSlider();
    }
    
    public void zoomIn() {
        if (frame.tree.getTree() == null) return;      
        frame.tree.setVerticalScaleFactor(frame.tree.getYScale()+minYScale); 
        zoomSlider.setValue((int)(frame.tree.getYScale()/minYScale));
        frame.tree.redraw();
    }
    
    public void zoomOut() {
        if (frame.tree.getTree() == null) return;
        if (frame.tree.getYScale()-minYScale >= minYScale)
            frame.tree.setVerticalScaleFactor(frame.tree.getYScale()-minYScale); 
        else
            frame.tree.setVerticalScaleFactor(minYScale); 
        
        zoomSlider.setValue((int)(frame.tree.getYScale()/minYScale));
        frame.tree.redraw();
    }

    public void setScale() {
        zoomSlider.setMaximum(frame.tree.getTree().getNumberOfLeaves());
        if (frame.tree.getTreeLayout().equals("Rectangular") || frame.tree.getTreeLayout().equals("Triangular")) {
            minYScale = (frame.tree.getHeight() - 2*frame.tree.getMargin())/frame.tree.getTree().getNumberOfLeaves();
        } else {
		    minYScale = (Math.min(frame.tree.getWidth(), frame.tree.getHeight())*0.5-frame.tree.getTreeMargin())/frame.tree.getTree().depth();
        }
    }
    
    public void syncTreeWithZoomSlider(JSlider slider) {
        if (frame.tree.getTree() == null) return;
        double newScale = minYScale * (slider.getValue());
        frame.tree.setVerticalScaleFactor(newScale);
        frame.tree.redraw();
    }
    
    public void syncTreeWithZoomSlider() {
        if (frame.tree.getTree() == null) return;
        double newScale = minYScale * (zoomSlider.getValue());
        frame.tree.setVerticalScaleFactor(newScale);
        frame.tree.redraw();
    }

    public void syncZoomSliderWithTree() {
        if (frame.tree.getTree() == null) return;
        zoomSlider.setValue((int)(frame.tree.getYScale()/minYScale));
    }

}
