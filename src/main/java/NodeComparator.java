import java.util.Comparator;

public class NodeComparator implements Comparator<HuffmanNode> {

    // For comparing the nodes
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.item - y.item;
    }
}
