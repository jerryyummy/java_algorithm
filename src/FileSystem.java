import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class TrieNode {
    Map<String, TrieNode> children = new TreeMap<>();
    String content = "";
    boolean isFile;
    public TrieNode() {}
}

public class FileSystem {
    TrieNode root;
    public FileSystem() {
        root = new TrieNode();
    }

    public List<String> ls(String path) {
        TrieNode node = root;
        List<String> res = new ArrayList<>();
        String[] file = path.split("/");
        if (file.length==0) return res;
        for (int i = 1; i < file.length-1; i++) {
            String curr = file[i];
            node = node.children.get(curr);
        }
        if (node.isFile){
            res.add(file[file.length-1]);
            return res;
        }else{
            for (Map.Entry<String,TrieNode> entry:node.children.entrySet()){
                res.add(entry.getKey());
            }
            return res;
        }

    }

    public void mkdir(String path) {
        TrieNode node = root;
        String[] file = path.split("/");
        for (int i = 1; i < file.length; i++) {
            String curr = file[i];
            node.children.putIfAbsent(curr,new TrieNode());
            node = node.children.get(curr);
        }

    }

    public void addContentToFile(String filePath, String content) {
        TrieNode node = root;
        String[] file = filePath.split("/");
        for (int i = 1; i < file.length; i++) {
            String curr = file[i];
            node.children.putIfAbsent(curr,new TrieNode());
            node = node.children.get(curr);
        }
        node.isFile = true;
        node.content+=content;
    }

    public String readContentFromFile(String filePath) {
        TrieNode node = root;
        String[] file = filePath.split("/");
        for (int i = 1; i < file.length; i++) {
            String curr = file[i];
            node = node.children.get(curr);
        }
        return node.content;
    }
}
