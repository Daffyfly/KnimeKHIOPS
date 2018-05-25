package org.vincent.khiops;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "KhiopsPredictor" Node.
 * 
 *
 * @author 
 */
public class KhiopsPredictorNodeFactory 
        extends NodeFactory<KhiopsPredictorNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public KhiopsPredictorNodeModel createNodeModel() {
        return new KhiopsPredictorNodeModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNrNodeViews() {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeView<KhiopsPredictorNodeModel> createNodeView(final int viewIndex,
            final KhiopsPredictorNodeModel nodeModel) {
        return new KhiopsPredictorNodeView(nodeModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDialog() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeDialogPane createNodeDialogPane() {
        return new KhiopsPredictorNodeDialog();
    }

}

