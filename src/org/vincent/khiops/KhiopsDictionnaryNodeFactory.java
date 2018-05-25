package org.vincent.khiops;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "KhiopsDictionnary" Node.
 * 
 *
 * @author 
 */
public class KhiopsDictionnaryNodeFactory 
        extends NodeFactory<KhiopsDictionnaryNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public KhiopsDictionnaryNodeModel createNodeModel() {
        return new KhiopsDictionnaryNodeModel();
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
    public NodeView<KhiopsDictionnaryNodeModel> createNodeView(final int viewIndex,
            final KhiopsDictionnaryNodeModel nodeModel) {
        return new KhiopsDictionnaryNodeView(nodeModel);
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
        return new KhiopsDictionnaryNodeDialog();
    }

}

