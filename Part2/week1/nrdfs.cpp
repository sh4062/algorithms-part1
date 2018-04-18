#include <iostream>
#include <stack>
using namespace std;

#define MaxNode 20
#define MAX 2000
#define StartNode 1

int map[MaxNode+1][MaxNode+1];

void dfs_stack(int start, int n){
    int visited[MaxNode],s_top;
    for(int i = 0;i <= MaxNode; i++){
        visited[i] = 0;
    }
    visited[start] = 1;
    stack <int> s;
    cout<<start<<" ";
    for(int i = 1; i <= n; i++){
        if(map[i][start] == 1 && !visited[i] ){
            visited[i] =  1;
            s.push(i);
        }
    }
    
    while(!s.empty()){
        s_top =  s.top();
        visited[s_top] = 1;
        cout<<s_top<<" ";
        s.pop();
        for(int i = 1; i <= n; i++){
            if(map[i][s_top] == 1 && !visited[i] ){
                visited[i] = 1;
                s.push(i);
            }
        }
    }
    
}

int main(int argc, const char * argv[]) {
    int num_edge,num_node;
    int x,y;
    cout<<"Input number of nodes and edges >"<<endl;
    cin>>num_node>>num_edge;
    for(int i =0;i<num_node;i++){
        for(int j=0;j<num_node;j++){
            map[i][j] = 0;
        }
    }
    for(int i = 1; i <= num_edge; i++){
        cin>>x>>y;
        map[x][y] = map[y][x] = 1;
    }
    
    dfs_stack(StartNode, num_node);

    return 0;
}
//dfs用栈模拟
//所有顶点的度均为偶数的任何连通图必然有欧拉回路
/*无向图存在欧拉回路的充要条件
一个无向图存在欧拉回路，当且仅当该图所有顶点度数都为偶数,且该图是连通图。
有向图存在欧拉回路的充要条件
一个有向图存在欧拉回路，所有顶点的入度等于出度且该图是连通图。
混合图存在欧拉回路条件
要判断一个混合图G（V,E）（既有有向边又有无向边）是欧拉图，方法如下：
假设有一张图有向图G'，在不论方向的情况下它与G同构。并且G'包含了G的所有有向边。那么如果存在一个图G'使得G'存在欧拉回路，那么G就存在欧拉回路。
*/