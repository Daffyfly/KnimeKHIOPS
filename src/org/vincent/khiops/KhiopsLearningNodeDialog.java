package org.vincent.khiops;

import javax.swing.JFileChooser;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentFileChooser;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelIntegerBounded;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

/**
 * <code>NodeDialog</code> for the "KhiopsLearning" Node.
 * 
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author 
 */
public class KhiopsLearningNodeDialog extends DefaultNodeSettingsPane {

    /**
     * New pane for configuring KhiopsLearning node dialog.
     * This is just a suggestion to demonstrate possible default dialog
     * components.
     */
    protected KhiopsLearningNodeDialog() {
        super();
        
        addDialogComponent(new DialogComponentFileChooser(
        		new SettingsModelString(
        				KhiopsLearningNodeModel.CFGKEY_EXEC, 
        				""),
        		"Khiops File", JFileChooser.OPEN_DIALOG, false));
        
        addDialogComponent(new DialogComponentFileChooser(
        		new SettingsModelString(
        				KhiopsLearningNodeModel.CFGKEY_DICT, 
        				""),
        		"Dictionnary File", JFileChooser.OPEN_DIALOG, false));
        
        addDialogComponent(new DialogComponentFileChooser(
        		new SettingsModelString(
        				KhiopsLearningNodeModel.CFGKEY_TRAINING, 
        				""),
        		"Training File", JFileChooser.OPEN_DIALOG, false));
        
        addDialogComponent(new DialogComponentString(
        		new SettingsModelString(
        				KhiopsLearningNodeModel.CFGKEY_PREDICTION, 
        				""),
        		"Variable to predict"));
        
        addDialogComponent(new DialogComponentString(
        		new SettingsModelString(
        				KhiopsLearningNodeModel.CFGKEY_SEPARATOR, 
        				""),
        		"Separator"));
        
        addDialogComponent(new DialogComponentFileChooser(
        		new SettingsModelString(
        				KhiopsLearningNodeModel.CFGKEY_RESULTDIR, 
        				""),
        		"Result directory", JFileChooser.OPEN_DIALOG, true));
        
                    
    }
}

