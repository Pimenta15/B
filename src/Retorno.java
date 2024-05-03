public class Retorno {
    private NodeB filhoDir;
    private boolean h;
    private Integer info;
    
    public Retorno() {}
    public Retorno(NodeB filhoDir, boolean h, Integer t) {
        this.filhoDir = filhoDir;
        this.h = h;
        this.info = t;
    }
    public Retorno(NodeB filhoDir, Integer t) {
        this.filhoDir = filhoDir;
        this.info = t;
    }
    public NodeB getFilhoDir() {
        return filhoDir;
    }
    public void setFilhoDir(NodeB filhoDir) {
        this.filhoDir = filhoDir;
    }
    public boolean isH() {
        return h;
    }
    public void setH(boolean h) {
        this.h = h;
    }
    public Integer getInfo() {
        return this.info;
    }
    public void setInfo(Integer t) {
        info = t;
    }
}
