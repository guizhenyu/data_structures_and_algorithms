package com.gzy.code.basic.class14;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * description: BestArrange date: 2022/6/30 10:22
 *
 * @author: guizhenyu
 */
public class BestArrange {

  /**
   * 条件：
   * 1. 有很多会议，每个会议的开始时间和结束时间不一样
   * 2. 怎么安排，安排的会议数量最多
   *
   *
   *
   */


  public static void main(String[] args) {
    int meetingSize = 12;
    int timeMax = 20;
    int testTimes = 100000;
    for (int i = 0; i < testTimes; i++){
      Meeting[] meetings = generateMeetings(meetingSize, timeMax);
      if(natureWisdom(meetings) != bestArrange(meetings)){
        System.out.println("Oops!");
      }
    }

    System.out.println("finish");
  }

  private static int bestArrange(Meeting[] meetings) {

    if (meetings == null || meetings.length == 0){
      return 0;
    }

    PriorityQueue<Meeting> meetingsHeap = new PriorityQueue<>(new MeetingComp());

    for (int i = 0; i < meetings.length; i++){
      meetingsHeap.add(meetings[i]);
    }

    int ans = 0;
    int endTime = 0;
    while (!meetingsHeap.isEmpty()){
      Meeting meeting = meetingsHeap.poll();
      if (meeting.startTime >= endTime){
        ans++;
        endTime = meeting.endTime;
      }

    }

    return ans;

  }



  private static int natureWisdom(Meeting[] meetings) {
    if (meetings == null || meetings.length == 0){
      return 0;
    }

    return process(meetings, 0, 0);
  }

  public static class MeetingComp implements Comparator<Meeting>{

    @Override
    public int compare(Meeting o1, Meeting o2) {
      return o1.endTime - o2.endTime;
    }
  }

  /**

   * @param meetings 会议的数组
   * @param done 已经安排的会议数量
   * @param timeLine
   * @return
   */
  private static int process(Meeting[] meetings, int done, int timeLine) {
    if (meetings.length == 0){
      return done;
    }

    int max = done;
    for (int i = 0; i < meetings.length; i++){
      if (meetings[i].startTime >= timeLine){
        // 将当前的会议从 meetings中删除
        Meeting[] freshMeetings = copyButExcept(meetings, i);

        max = Math.max(max, process(freshMeetings, done + 1, meetings[i].endTime));
      }
    }

    return max;
  }

  private static Meeting[] copyButExcept(Meeting[] meetings, int index) {
    Meeting[] ans = new Meeting[meetings.length - 1];
    int ansi = 0;
    for (int i = 0; i < meetings.length; i++){
      if (i != index){
        ans[ansi++] = meetings[i];
      }
    }

    return ans;
  }

  private static Meeting[] generateMeetings(int meetingSize, int timeMax) {
    Meeting[] meetings = new Meeting[(int) (Math.random() * (meetingSize + 1))];
    for (int i = 0; i < meetings.length; i++){
      int startTime = (int)(Math.random() * timeMax) + 1;
      int endTime = (int)(Math.random() * timeMax) + 1;
      if (startTime == endTime){
        meetings[i] = new Meeting(startTime, endTime + 1);
      }else {

        meetings[i] = new Meeting(Math.min(startTime, endTime), Math.max(startTime, endTime));
      }

    }
    return meetings;
  }

  public static class Meeting{
    int startTime;
    int endTime;

    public Meeting(int startTime, int endTime){
      this.startTime = startTime;
      this.endTime = endTime;
    }
  }

}
