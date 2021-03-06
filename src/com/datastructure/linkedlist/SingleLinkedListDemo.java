package com.datastructure.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {

        //测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "庐俊毅", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        //测试单链表反转
//        System.out.println("原始单链表为：");
//        singleLinkedList.list();
//        reverseList(singleLinkedList.getHead());
//        System.out.println("反转后链表为：");
//        singleLinkedList.list();

        //测试逆序打印单链表
        System.out.println("原始单链表为：");
        singleLinkedList.list();
        System.out.println("逆序链表为：");
        reversePrint(singleLinkedList.getHead());


        //按编号加入
//        singleLinkedList.addByOrder(hero1);
//        singleLinkedList.addByOrder(hero4);
//        singleLinkedList.addByOrder(hero3);
//        singleLinkedList.addByOrder(hero2);

        //显示链表
//        singleLinkedList.list();

        //测试修改节点
//        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟******");
//        singleLinkedList.update(newHeroNode);
//        System.out.println("修改节点后的链表");
//        singleLinkedList.list();

        //删除一个节点
//        singleLinkedList.delete(1);
//        System.out.println("删除后的链表");
//        singleLinkedList.list();

        //求链表中有效节点的个数
//        System.out.println("链表中有效节点的个数："+ getLength(singleLinkedList.getHead()));
    }

    //将单链表反转
    public static void reverseList(HeroNode heroNode){
        if (heroNode.next == null || heroNode.next.next == null){           //链表为空或者只有一个节点，直接返回，不需要反转
            return;
        }

        HeroNode cur = heroNode.next;
        HeroNode next = null;                                               //指向当前节点cur的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");

        while (cur != null){
            next = cur.next;                     //暂时保存当前节点的下一个节点
            cur.next = reverseHead.next;         //将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;              //将cur连接到新的链表上
            cur = next;                          //让cur后移
        }

        heroNode.next = reverseHead.next;        //将heroNOde.next 指向reverseHead.next ，实现单链表的反转
    }

    //逆序打印单链表
    public static void reversePrint(HeroNode heroNode){
        if (heroNode.next == null){
            return;                                          // 空链表
        }

        Stack<HeroNode> heroNodeStack = new Stack<>();       //创建一个栈，将各个节点压入栈

        HeroNode cur = heroNode.next;

        while (cur != null){
            heroNodeStack.push(cur);                         //入栈
            cur = cur.next;
        }

        while (heroNodeStack.size()>0){
            System.out.println(heroNodeStack.pop());        //出栈
        }
    }

    //查找单链表中倒数第k个节点
    //1.编写一个方法，获取head节点，同时接收一个index ，index 表示是倒数第index个节点
    //2.先把链表从头到尾遍历，得到链表的总长度  getLength
    //3.得到size后，我们从链表的第一个开始遍历(size - index)个，就可以得到
    public static HeroNode findLastIndexNode(HeroNode heroNode,int index){
        if (heroNode.next == null){
            return null;
        }

        int size = getLength(heroNode);                 //得到链表的总长度

        if (index <= 0 || index > size){
            return null;                                //对index做一个校验
        }

        HeroNode cur = heroNode.next;
        for (int i = 0; i <size - index ; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 获取单链表的节点的个数（如果是带头结点的链表，需求不许统计头节点）
     * @param heroNode 链表的头结点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode heroNode){
        if (heroNode.next == null){                          //空链表
            return 0;
        }
        int length = 0;
        HeroNode temp = heroNode.next;
        while (temp != null){
            length ++;
            temp = temp.next;
        }
        return length;
    }
}

class SingleLinkedList {

    private HeroNode head = new HeroNode(0, "", "");   //先初始化一个头节点，头结点不动，不存放具体的数据

    public HeroNode getHead() {
        return head;
    }

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

    //根据排名插到指定位置（如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;                                        //flag添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {                                   //说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {                          //位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {                   //说明编号已经存在
                flag = true;
                break;
            }
            temp = temp.next;                                        //后移，继续遍历
        }
        if (flag) {
            System.out.println(heroNode.no + "编号已经存在，不能插入");
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息，根据no编号来修改，即no编号不能改
    public void update(HeroNode newHeroNode) {               //根据newHeroNode 的 no来修改

        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head.next;
        boolean flag = false;                               //表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;                                     //已经遍历完列表
            }
            if (temp.no == newHeroNode.no) {
                flag = true;                               //找到
                break;
            }
            temp = temp.next;
        }
        if (flag) {                                        //根据flag判断是否找到要修改的节点
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.println("没有找到编号为" + newHeroNode.no + "的节点");
        }
    }

    //删除节点
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;                          //是否找到待删除节点
        while (true){
            if (temp.next == null){                    //已到链表的最后
                break;
            }
            if(temp.next.no == no){
                flag = true;                          //找到要删除节点的前一个节点
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        }else {
            System.out.println("要删除的节点" + no + "不存在");
        }
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