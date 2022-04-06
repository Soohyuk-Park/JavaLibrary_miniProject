import java.io.IOException;
import java.util.ArrayList;

public interface MemberInterface extends Utils {

    void add(Member member) throws IOException;

    void delete(ArrayList<Member> memberList) throws IOException;

    void setting(Member member);

    void viewAll(Member member) throws IOException;

    void changePassword( ArrayList<Member> memberList ) throws IOException;

    ArrayList<Member> getMemberInstanceList( ArrayList<Member> L ) throws IOException;

    static int getBlankRowMember() throws IOException {

        return 0;

    }
}
