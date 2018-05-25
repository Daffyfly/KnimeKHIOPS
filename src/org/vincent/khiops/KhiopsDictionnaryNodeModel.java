package org.vincent.khiops;

import java.io.File;
import java.io.IOException;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataTableSpecCreator;
import org.knime.core.data.DoubleValue;
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
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeLogger;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;


/**
 * This is the model implementation of KhiopsDictionnary.
 * 
 *
 * @author 
 */
public class KhiopsDictionnaryNodeModel extends NodeModel {
    
    // the logger instance
    private static final NodeLogger logger = NodeLogger
            .getLogger(KhiopsDictionnaryNodeModel.class);
    
    static final String CFGKEY_RESULTDIR = "Result directory";

	private SettingsModelString m_resultdir = 
			new SettingsModelString(KhiopsDictionnaryNodeModel.CFGKEY_RESULTDIR,"");
        
    /** the settings key which is used to retrieve and 
        store the settings (from the dialog or from a settings file)    
       (package visibility to be usable from the dialog). */
    

    /**
     * Constructor for the node model.
     */
    protected KhiopsDictionnaryNodeModel() {
    
        // TODO one incoming port and one outgoing port is assumed
        super(1, 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected BufferedDataTable[] execute(final BufferedDataTable[] inData,
            final ExecutionContext exec) throws Exception {

    	String resdir = m_resultdir.getStringValue() + "\\";
    	KhiopsDictionnaryComposer dict = new KhiopsDictionnaryComposer(resdir);
    	
    	BufferedDataTable table = inData[0];
    	DataTableSpec tableSpec = table.getSpec();
    	
    	int size = tableSpec.getNumColumns();
    	

    	DataTableSpecCreator crator = new DataTableSpecCreator();
		crator.addColumns(new DataColumnSpecCreator("name", StringCellFactory.TYPE).createSpec());
		crator.addColumns(new DataColumnSpecCreator("type", StringCellFactory.TYPE).createSpec());
    	
    	BufferedDataContainer container = exec.createDataContainer(crator.createSpec());
    	
    	for(int index = 0; index<size; index++){
    		DataColumnSpec columnspec = tableSpec.getColumnSpec(index);
    		String type = "";
    		if(columnspec.getType().isCompatible(DoubleValue.class)){
    			type = "Numerical";
    		}else{
    			type = "Categorical";
    		}
    		container.addRowToTable(
    				new DefaultRow(new RowKey(String.valueOf(index)), new DataCell[] { 
    						StringCellFactory.create(columnspec.getName()),
    						StringCellFactory.create(type)
					}));
    		dict.addField(columnspec.getName(), type);
    	}
    	
    	dict.close();
    	


    	
        container.close();
        

    	DataTableSpecCreator crator2 = new DataTableSpecCreator();
		crator2.addColumns(new DataColumnSpecCreator("path", StringCellFactory.TYPE).createSpec());
    	
    	
        BufferedDataContainer container2 = exec.createDataContainer(crator2.createSpec());
    	
		container2.addRowToTable(
				new DefaultRow(new RowKey(String.valueOf(0)), new DataCell[] { 
						StringCellFactory.create(resdir + "dictionnary.kdic")
				}));
    	


    	
        container2.close();
        
        return new BufferedDataTable[]{container.getTable(), container2.getTable()};
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
    protected DataTableSpec[] configure(final DataTableSpec[] inSpecs)
            throws InvalidSettingsException {
        
        // TODO: check if user settings are available, fit to the incoming
        // table structure, and the incoming types are feasible for the node
        // to execute. If the node can execute in its current state return
        // the spec of its output data table(s) (if you can, otherwise an array
        // with null elements), or throw an exception with a useful user message

        return new DataTableSpec[]{null, null};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettingsTo(final NodeSettingsWO settings) {

        // TODO save user settings to the config object.
        
    	m_resultdir.saveSettingsTo(settings);

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
        
    	m_resultdir.loadSettingsFrom(settings);

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


    	m_resultdir.validateSettings(settings);

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

