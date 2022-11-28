import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/*
华华通过以下方式进行分析，首先将比赛每个球的胜负列成一张表，然后分别计算在 11 分制和 21 分制下，双方的比赛结果（截至记录末尾）。

比如现在有这么一份记录，（其中 W 表示华华获得一分，L 表示华华对手获得一分）：

WWWWWWWWWWWWWWWWWWWWWWLW

在 11 分制下，此时比赛的结果是华华第一局 11 比 0 获胜，第二局 11 比 0 获胜，正在进行第三局，当前比分 1 比 1。而在 21 分制下，此时比赛结果是华华第一局 21 比 0 获胜，正在进行第二局，比分 2 比 1。如果一局比赛刚开始，则此时比分为 0 比 0。直到分差大于或者等于 2，才一局结束。

你的程序就是要对于一系列比赛信息的输入（WL 形式），输出正确的结果。

输入格式
每个输入文件包含若干行字符串，字符串有大写的  W、  L 和 E 组成。其中 E 表示比赛信息结束，程序应该忽略 E 之后的所有内容。

输出格式
输出由两部分组成，每部分有若干行，每一行对应一局比赛的比分（按比赛信息输入顺序）。其中第一部分是 11 分制下的结果，第二部分是 21 分制下的结果，两部分之间由一个空行分隔。

输入输出样例
 */
class GameResult{
    public String result;
    public GameResult(String result){
        this.result = result;
    }
    public void transforTo21(){
        HashMap<Character,Integer> hashMap = new HashMap<>();
        hashMap.put('W',0);hashMap.put('L',0);
        for(char ch : this.result.toCharArray()){
            if (ch=='W'){
                hashMap.replace('W',hashMap.get('W')+1);
            }else {
                hashMap.replace('L',hashMap.get('L')+1);
            }
            if ((Math.max(hashMap.get('W'),hashMap.get('L'))==21 && Math.abs(hashMap.get('W')-hashMap.get('L'))>=2)
                || (Math.min(hashMap.get('W'),hashMap.get('L'))==20 && Math.abs(hashMap.get('W')-hashMap.get('L'))>=2)){
                System.out.println(hashMap.get('W')+":"+hashMap.get('L'));
                hashMap.replace('W',0);
                hashMap.replace('L',0);
            }
        }
        System.out.println(hashMap.get('W')+":"+hashMap.get('L'));
    }

    public void transforTo11(){
        HashMap<Character,Integer> hashMap = new HashMap<>();
        hashMap.put('W',0);hashMap.put('L',0);
        for(char ch : this.result.toCharArray()){
            if (ch=='W'){
                hashMap.replace('W',hashMap.get('W')+1);
            }else {
                hashMap.replace('L',hashMap.get('L')+1);
            }
            if ((Math.max(hashMap.get('W'),hashMap.get('L'))==11 && Math.abs(hashMap.get('W')-hashMap.get('L'))>=2)
                    || (Math.min(hashMap.get('W'),hashMap.get('L'))==10 && Math.abs(hashMap.get('W')-hashMap.get('L'))>=2)){
                System.out.println(hashMap.get('W')+":"+hashMap.get('L'));
                hashMap.replace('W',0);
                hashMap.replace('L',0);
            }
        }
        System.out.println(hashMap.get('W')+":"+hashMap.get('L'));
    }

}
public class PingPong {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder string = new StringBuilder();
        while (true){
            String line = bufferedReader.readLine();
            if (line==null){
                break;
            }else {
                if (line.indexOf('E')!=-1){
                    string.append(line.substring(0, line.indexOf('E')));
                    break;
                }else {
                    string.append(line);
                }
            }
        }
        GameResult gameResult = new GameResult(string.toString());
        gameResult.transforTo11();
        System.out.print('\n');
        gameResult.transforTo21();
    }
}
