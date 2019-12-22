package com.datastructure.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {

        //测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"庐俊毅","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        singleLinkedList.list();

    }
}

class SingleLinkedList {

    private HeroNode head = new HeroNode(0, "", "");   //先初始化一个头节点，头结点不动，不存放具体的数据

    //添加节点到单向链表  不考虑编号顺序时
    //1.找到当前链表的最后节点    2.将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode) {

        HeroNode temp = head;                 //因为head节点不能动，因此我们需要一个辅助遍历temp

        while (true) {                        //遍历链表，找到最后
            if (temp.next == null) {          //找到链表的最后
                break;
            }
            temp = temp.next;                 //如果没有找到最后，将temp后移
        }

        temp.next = heroNode;                 //当退出while循环时，temp就指向了链表的最后，将最后这个节点的next指向新的节点
    }

    //显示链表，遍历
    public void list() {
        if (head.next == null) {                                   //判断链表为空
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head.next;                                 //因为head节点不能动，因此我们需要一个辅助遍历temp
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);                              //输出节点的信息
            temp = temp.next;                                      //将temp后移
        }
    }
}

//定义HeroNode，每个 HeroNode 对象就是一个节点
class HeroNode {

    public int no;
    public String name;
    public String nickname;
    public HeroNode next;               //指向下一个节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
//                ", next=" + next +
                '}';
    }
}