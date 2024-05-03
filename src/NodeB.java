public class NodeB {
    private int n; // numero de keys
    private Integer keys[]; // keys
    private NodeB filhos[];
    
    
    
    public int getN() {
        return n;
    }
    
    public void setN(int n) {
        this.n = n;
    }
    
    public int getKeys(int i) {
        return keys[i];
    }
    
    public void setKeys(int i, Integer value) {
        this.keys[i] = value;
    }
    public NodeB getFilhos(int i ) {
        return this.filhos[i];
    }

    
    public void setFilhos(int i , NodeB filho) {
        this.filhos[i] = filho;
    }
    public NodeB(int m) {
        this.keys = new Integer[m];
        this.filhos = new NodeB[m];
    }

    int binarySearch(Integer  value) {
        int mid, i, f, comp;
        i = 0;
        f = this.n -1;
        while (i <= f) {
            mid = (i + f) / 2;
            comp = value.compareTo(this.keys[mid]);
            if (comp == 0) {
                return mid;
                 
                } else if (comp < 0) {
                    f = mid -1;
                } else {
                    i = mid + 1;
                }
            }return i;
            
        }
 
        void insertKeys(Integer value, NodeB filhoDir) {
            int pos;
            int k = this.n;
            
            pos = binarySearch(value);
            
            while (k > pos && value.compareTo(this.keys[k-1]) < 0) {  
                     this.filhos[k + 1] = this.filhos[k];
                     this.keys[k] = this.keys[k-1];
                     k--;
                    }
                    
                    this.keys[pos] = value;
                    this.filhos[pos + 1] = filhoDir;
                    this.n++;
            }

            public void removeKeys(int pos) {
                
                for (int i = pos; i < this.n - 1; i++) {
                    this.keys[i] = this.keys[i + 1];
                }
                this.keys[this.n - 1] = 0;
            
                
                for (int i = pos + 1; i <= this.n; i++) {
                    this.filhos[i - 1] = this.filhos[i];
                }
                this.filhos[this.n] = null;
            
                
                this.n--;
            }
            
            
            

        void preencherRaiz(int value, NodeB r, NodeB filhoDir) {
            this.keys[0] = value;
            this.filhos[0] = r;
            this.filhos[1] = filhoDir;
            this.n = 1;
            }
        
       
}

