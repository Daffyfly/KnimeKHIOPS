package org.vincent.khiops;

import java.io.File;
import java.io.IOException;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataTableSpecCreator;
import org.knime.core.data.RowKey;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.data.def.StringCell.StringCellFactory;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.CanceledExecutionException;
import org.knime.core.node.defaultnodesettings.SettingsModelIntegerBounded;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.knime.core.node.port.PortObject;
import org.knime.core.node.port.PortObjectSpec;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeLogger;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;


/**
 * This is the model implementation of KhiopsLearning.
 * 
 *
 * @author 
 */
public class KhiopsLearningNodeModel extends NodeModel {
    
    // the logger instance
    private static final NodeLogger logger = NodeLogger
            .getLogger(KhiopsLearningNodeModel.class);
        
    /** the settings key which is used to retrieve and 
        store the settings (from the dialog or from a settings file)    
       (package visibility to be usable from the dialog). */
	static final String CFGKEY_EXEC = "Execfile";
	static final String CFGKEY_DICT = "Dictfile";
	static final String CFGKEY_TRAINING = "TrainingDatafile";
	static final String CFGKEY_SEPARATOR = "Separator";
	static final String CFGKEY_RESULTDIR = "ResultDir";
	static final String CFGKEY_PREDICTION = "Prediction";
	
	private SettingsModelString m_exec = 
			new SettingsModelString(KhiopsLearningNodeModel.CFGKEY_EXEC,"");
	
	private SettingsModelString m_dict = 
			new SettingsModelString(KhiopsLearningNodeModel.CFGKEY_DICT,"");
	
	private SettingsModelString m_training = 
			new SettingsModelString(KhiopsLearningNodeModel.CFGKEY_TRAINING,"");
	
	private SettingsModelString m_separator = 
			new SettingsModelString(KhiopsLearningNodeModel.CFGKEY_SEPARATOR,"");
	
	private SettingsModelString m_resultdir = 
			new SettingsModelString(KhiopsLearningNodeModel.CFGKEY_RESULTDIR,"");
	
	private SettingsModelString m_prediction = 
			new SettingsModelString(KhiopsLearningNodeModel.CFGKEY_PREDICTION, "");


    /**
     * Constructor for the node model.
     */
    protected KhiopsLearningNodeModel() {
    
        // TODO one incoming port and one outgoing port is assumed
        super(0, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PortObject[] execute(final PortObject[] inData,
            final ExecutionContext exec) throws Exception {

    	String resdir = m_resultdir.getStringValue() + "\\";
    	
    	KhiopsTrainingComposer khiops = new KhiopsTrainingComposer(m_dict.getStringValue(), 
    			m_training.getStringValue(),
    			m_separator.getStringValue(),
    			resdir,
    			m_prediction.getStringValue());
    	
    	String trainingfile = khiops.compose(resdir);
    	
    	DataTableSpecCreator crator = new DataTableSpecCreator();
		crator.addColumns(new DataColumnSpecCreator("Path", StringCellFactory.TYPE).createSpec());
    	
    	BufferedDataContainer container = exec.createDataContainer(crator.createSpec());


    	container.addRowToTable(
				new DefaultRow(new RowKey("Path"), new DataCell[] { StringCellFactory.create(resdir + "Modeling.kdic") }));


        
        boolean isWindows = System.getProperty("os.name")
        		  .toLowerCase().startsWith("windows");

    	Process process;
    	if (isWindows) {
    		System.out.println(m_exec.getStringValue() + " -i " + trainingfile);
    	    process = Runtime.getRuntime()
    	      .exec(String.format("cmd.exe /c \"" + m_exec.getStringValue() + "\" -i " + trainingfile));

        } 
    	

        container.close();
    	
        return new PortObject[]{ container.getTable() };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reset() {
        // TODO Code executed on reset.
        // Models build during execute are cleared here.
        // Also data handled in load/saveInternals will be erased here.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PortObjectSpec[] configure(final PortObjectSpec[] inSpecs)
            throws InvalidSettingsException {
        
        // TODO: check if user settings are available, fit to the incoming
        // table structure, and the incoming types are feasible for the node
        // to execute. If the node can execute in its current state return
        // the spec of its output data table(s) (if you can, otherwise an array
        // with null elements), or throw an exception with a useful user message

        return new PortObjectSpec[]{null};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettingsTo(final NodeSettingsWO settings) {

        // TODO save user settings to the config object.
        
        m_exec.saveSettingsTo(settings);
        m_dict.saveSettingsTo(settings);
        m_training.saveSettingsTo(settings);
        m_separator.saveSettingsTo(settings);
        m_resultdir.saveSettingsTo(settings);
    	m_prediction.saveSettingsTo(settings);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings)
            throws InvalidSettingsException {
            
        // TODO load (valid) settings from the config object.
        // It can be safely assumed that the settings are valided by the 
        // method below.
        
    	m_exec.loadSettingsFrom(settings);
    	m_dict.loadSettingsFrom(settings);
    	m_training.loadSettingsFrom(settings);
    	m_separator.loadSettingsFrom(settings);
    	m_resultdir.loadSettingsFrom(settings);
    	m_prediction.loadSettingsFrom(settings);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validateSettings(final NodeSettingsRO settings)
            throws InvalidSettingsException {
            
        // TODO check if the settings could be applied to our model
        // e.g. if the count is in a certain range (which is ensured by the
        // SettingsModel).
        // Do not actually set any values of any member variables.

    	m_exec.validateSettings(settings);
    	m_dict.validateSettings(settings);
    	m_training.validateSettings(settings);
    	m_separator.validateSettings(settings);
    	m_resultdir.validateSettings(settings);
    	m_prediction.validateSettings(settings);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadInternals(final File internDir,
            final ExecutionMonitor exec) throws IOException,
            CanceledExecutionException {
        
        // TODO load internal data. 
        // Everything handed to output ports is loaded automatically (data
        // returned by the execute method, models loaded in loadModelContent,
        // and user settings set through loadSettingsFrom - is all taken care 
        // of). Load here only the other internals that need to be restored
        // (e.g. data used by the views).

    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveInternals(final File internDir,
            final ExecutionMonitor exec) throws IOException,
            CanceledExecutionException {
       
        // TODO save internal models. 
        // Everything written to output ports is saved automatically (data
        // returned by the execute method, models saved in the saveModelContent,
        // and user settings saved through saveSettingsTo - is all taken care 
        // of). Save here only the other internals that need to be preserved
        // (e.g. data used by the views).

    }

}

