package testCase;

/**
 * @Description:
 * @auther phantom
 * @create 2019-12-04 下午9:07
 */
public class TestCase4CUBS {
    private String planType;
    private int planFee;
    private int talkTime;
    private int flow;

    public TestCase4CUBS(String planType, int planFee, int talkTime, int flow){
        setPlanType(planType);
        setPlanFee(planFee);
        setTalkTime(talkTime);
        setFlow(flow);
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public int getPlanFee() {
        return planFee;
    }

    public void setPlanFee(int planFee) {
        this.planFee = planFee;
    }

    public int getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(int talkTime) {
        this.talkTime = talkTime;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }
}
