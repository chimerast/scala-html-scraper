package org.jaxen.jericho;

import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import org.jaxen.BaseXPath;
import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.Navigator;
import org.jaxen.util.SingletonList;

public class JerichoXPath extends BaseXPath {
    private static final long serialVersionUID = 1L;

    public JerichoXPath(String xpathExpr, Navigator navigator)
            throws JaxenException {
        super(xpathExpr, navigator);
    }

    public JerichoXPath(String xpathExpr) throws JaxenException {
        super(xpathExpr, DocumentNavigator.getInstance());
    }

    /**
     * Jericho specific method to get the context associated with a node.
     * 
     * @param node
     *            the current node being visited.
     * @return the Context associated with the node.
     */
    @Override
    protected Context getContext(Object node) {
        if (node instanceof Context) {
            return (Context) node;
        }
        Context fullContext = new Context(getContextSupport());
        if (node instanceof Source) {
            Element rootNode = (Element) getNavigator().getDocumentNode(node);
            fullContext.setNodeSet(new SingletonList(rootNode));
        } else if (node instanceof List<?>) {
            fullContext.setNodeSet((List<?>) node);
        } else {
            List<?> list = new SingletonList(node);
            fullContext.setNodeSet(list);
        }
        return fullContext;
    }
}
