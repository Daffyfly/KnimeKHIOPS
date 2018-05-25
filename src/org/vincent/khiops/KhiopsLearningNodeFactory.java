package org.vincent.khiops;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "KhiopsLearning" Node.
 * 
 *
 * @author 
 */
public class KhiopsLearningNodeFactory 
        extends NodeFactory<KhiopsLearningNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public KhiopsLearningNodeModel createNodeModel() {
        return new KhiopsLearningNodeModel();
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
    public NodeView<KhiopsLearningNodeModel> createNodeView(final int viewIndex,
            final KhiopsLearningNodeModel nodeModel) {
        return new KhiopsLearningNodeView(nodeModel);
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
        return new KhiopsLearningNodeDialog();
    }

}

