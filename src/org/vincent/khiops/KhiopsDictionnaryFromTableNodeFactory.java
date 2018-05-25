package org.vincent.khiops;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "KhiopsDictionnaryFromTable" Node.
 * 
 *
 * @author 
 */
public class KhiopsDictionnaryFromTableNodeFactory 
        extends NodeFactory<KhiopsDictionnaryFromTableNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public KhiopsDictionnaryFromTableNodeModel createNodeModel() {
        return new KhiopsDictionnaryFromTableNodeModel();
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
    public NodeView<KhiopsDictionnaryFromTableNodeModel> createNodeView(final int viewIndex,
            final KhiopsDictionnaryFromTableNodeModel nodeModel) {
        return new KhiopsDictionnaryFromTableNodeView(nodeModel);
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
        return new KhiopsDictionnaryFromTableNodeDialog();
    }

}

