import java.util.LinkedList;
import java.util.Queue;

public class ArvoreB {
    private NodeB root;

    private int order;
    private int nKeys; // Número máximo de chaves em um nó da árvore
    private int min; // Número mínimo de chaves em um nó da árvore

    public void setRoot(NodeB root) {
        this.root = root;
    }

    public NodeB getRoot() {
        return root;
    }

    public int getOrder() {
        return order;
    }

    public int getnKeys() {
        return nKeys;
    }

    public int getmin() {
        return min;
    }

    public ArvoreB(int m) {
        this.order = m;
        this.nKeys = m - 1;
        this.min = m / 2;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(Integer info) {
        Integer infoRetorno;

        if (this.isEmpty() == true) {

            this.root = new NodeB(this.order);
            this.root.preencherRaiz(info, null, null);

        } else {

            Retorno result = new Retorno();
            insertB(this.root, info, result);
            boolean h = result.isH();
            if (h) {

                NodeB filhoDir = result.getFilhoDir();
                infoRetorno = result.getInfo();
                NodeB novaRaiz = new NodeB(this.order);
                novaRaiz.preencherRaiz(infoRetorno, this.root, filhoDir);
                this.root = novaRaiz;

            }

        }
    }

    private void insertB(NodeB raiz, Integer info, Retorno result) {
        int i, pos;
        Integer infoMediano;

        if (raiz == null) {
            result.setH(true);
            result.setInfo(info);

        } else {

            pos = raiz.binarySearch(info);

            if (pos < raiz.getN() && raiz.getKeys(pos) == info) {
                System.out.println("Chave já contida na árvore");
                result.setH(false);

            } else {

                this.insertB(raiz.getFilhos(pos), info, result);

                if (result.isH()) {

                    if (raiz.getN() < this.nKeys) {

                        raiz.insertKeys(result.getInfo(), result.getFilhoDir());
                        result.setH(false);

                    } else {

                        NodeB temp = new NodeB(this.order);

                        infoMediano = raiz.getKeys(this.min);
                        if (pos == this.min) {
                            infoMediano = info;
                        }
                        temp.setFilhos(0, raiz.getFilhos(this.min + 1));
                        for (i = this.min + 1; i < this.nKeys; i++) {
                            temp.insertKeys(raiz.getKeys(i), raiz.getFilhos(i + 1));
                        }
                        if (pos == this.min) {
                            result.setInfo(raiz.getKeys(this.min));
                        }

                        for (i = this.min; i < this.nKeys; i++) {

                            raiz.setKeys(i, null);
                            raiz.setFilhos(i + 1, null);

                        }

                        raiz.setN(this.min);

                        if (pos < this.min) {

                            raiz.insertKeys(result.getInfo(), result.getFilhoDir());

                        } else {

                            temp.insertKeys(result.getInfo(), result.getFilhoDir());

                        }
                        result.setInfo(infoMediano);

                        result.setFilhoDir(temp);

                    }
                }
            }
        }
    }

    

    public Retorno findMaxKey() {
        if (isEmpty()) {
            return null;
        } else {
            NodeB current = root;

            while (current.getFilhos(current.getN()) != null) {
                current = current.getFilhos(current.getN());
            }

            int position = current.getN() - 1;
            return new Retorno(current, position);
        }
    }

    public Retorno findMinKey() {
        if (isEmpty()) {
            return null;
        } else {
            NodeB current = root;

            while (current.getFilhos(0) != null) {
                current = current.getFilhos(0);
            }

            return new Retorno(current, 0);
        }
    }

    public int height() {
        if (isEmpty()) {
            return -1;
        }

        NodeB current = root;
        int height = 0;

        while (current.getFilhos(0) != null) {
            current = current.getFilhos(0);
            height++;
        }

        return height;
    }

    public Retorno find(int value) {
        return find(root, value);
    }

    private Retorno find(NodeB node, int value) {
        if (node == null) {
            return null;
        } else {

            int i = 0;
            while (i < node.getN() && value > node.getKeys(i)) {
                i++;
            }

            if (i < node.getN() && value == node.getKeys(i)) {
                return new Retorno(node, i);
            }

            return find(node.getFilhos(i), value);
        }
    }

    public void displayByLevel() {
        if (isEmpty()) {
            System.out.println("Árvore vazia!");
            return;
        }
    
        Queue<NodeB> queue = new LinkedList<>();
        queue.add(root);
        System.out.println("Passeio por Nível:");
        while (!queue.isEmpty()) {
            int nodesAtCurrentLevel = queue.size();
    
            while (nodesAtCurrentLevel > 0) {
                NodeB currentNode = queue.poll();
                for (int i = 0; i < currentNode.getN(); i++) {
                    System.out.print(currentNode.getKeys(i));
                    if (i < currentNode.getN() - 1) {
                        System.out.print(" | ");
                    }
                }
    
                for (int i = 0; i <= currentNode.getN(); i++) {
                    if (currentNode.getFilhos(i) != null) {
                        queue.add(currentNode.getFilhos(i));
                    }
                }
                nodesAtCurrentLevel--;
                if (nodesAtCurrentLevel > 0) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
    }
    
    

    public void displayPreOrder() {
        if (isEmpty()) {
            System.out.println("arvore vazia!");
            return;
        }

        System.out.println("passeio pre-order:");
        displayPreOrder(root);
        System.out.println();
    }

    private void displayPreOrder(NodeB node) {
        if (node != null) {

            for (int i = 0; i < node.getN(); i++) {
                System.out.print(node.getKeys(i) + " ");
            }
            System.out.println();
            for (int i = 0; i <= node.getN(); i++) {
                displayPreOrder(node.getFilhos(i));
            }
        }
    }

    
public void remove(Integer info) {
    
    if (isEmpty()) {
        System.out.println("Árvore vazia! Não há nada para remover.");
        return;
    }

    
    Retorno result = new Retorno();
    
    remove(root, info, result);
    
    if (result.isH()) {
        root = result.getFilhoDir();
        
        if (root != null) {
            root.setN(root.getN() - 1);
        }
    }
}


private void remove(NodeB raiz, Integer info, Retorno result) {
    int pos;
   
    if (raiz == null) {
        System.out.println("Chave não encontrada na árvore.");
        
        result.setH(false);
    } else {
        
        pos = raiz.binarySearch(info);
        
        if (pos < raiz.getN() && raiz.getKeys(pos) == info) { 
            
            if (raiz.getFilhos(0) == null) {
                
                raiz.removeKeys(pos);
                
                if (raiz == root && raiz.getN() == 0) {
                    root = null;
                    result.setH(true);
                }
            } else {
                
                NodeB pred = raiz.getFilhos(pos);
                while (pred.getFilhos(pred.getN()) != null) {
                    pred = pred.getFilhos(pred.getN());
                }
                
                raiz.setKeys(pos, pred.getKeys(pred.getN() - 1));
               
                remove(raiz.getFilhos(pos), pred.getKeys(pred.getN() - 1), result);
                
                if (result.isH()) {
                    pred.removeKeys(pred.getN() - 1);
                }
            }
        } else { 
            remove(raiz.getFilhos(pos), info, result);
            
            if (result.isH()) {
                
                raiz.removeKeys(pos);
                
                if (raiz.getFilhos(pos) != null && raiz.getFilhos(pos).getN() < this.min) {
                    balance(raiz, pos);
                } else {
                    
                    result.setH(false);
                }
            }
        }
    }
}

private void balance(NodeB raiz, int pos) {
    NodeB filho = raiz.getFilhos(pos);
    NodeB irmao = (pos == 0) ? raiz.getFilhos(pos + 1) : raiz.getFilhos(pos - 1);

    if (irmao.getN() > this.min) {
        
        if (pos != 0) {
            
            filho.insertKeys(raiz.getKeys(pos - 1), irmao.getFilhos(irmao.getN()));
            raiz.setKeys(pos - 1, irmao.getKeys(irmao.getN() - 1));
            irmao.removeKeys(irmao.getN() - 1);
        } else {
            
            filho.insertKeys(raiz.getKeys(pos), filho.getFilhos(0));
            raiz.setKeys(pos, irmao.getKeys(0));
            irmao.removeKeys(0);
        }
    } else {
        
        if (pos != 0) {
            
            irmao.insertKeys(raiz.getKeys(pos - 1), filho.getFilhos(filho.getN()));
            for (int i = 0; i < filho.getN(); i++) {
                irmao.insertKeys(filho.getKeys(i), filho.getFilhos(i));
            }
            irmao.insertKeys(filho.getKeys(filho.getN() - 1), filho.getFilhos(filho.getN()));
            raiz.removeKeys(pos - 1);
            
        } else {
            
            filho.insertKeys(raiz.getKeys(pos), irmao.getFilhos(0));
            for (int i = 0; i < irmao.getN(); i++) {
                filho.insertKeys(irmao.getKeys(i), irmao.getFilhos(i));
            }
            filho.insertKeys(irmao.getKeys(irmao.getN() - 1), irmao.getFilhos(irmao.getN()));
            raiz.removeKeys(pos);
        
        }
    }
}




}