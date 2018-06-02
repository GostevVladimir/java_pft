package ru.stqa.pft.rest.tests;



import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import java.io.IOException;


public class TestBase {

  public boolean isIssueOpen(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId)))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues").getAsJsonArray();
    JsonElement testIssue = issues.getAsJsonArray().get(0).getAsJsonObject();
    String issueStatus = testIssue.getAsJsonObject().get("state_name").getAsString();
    if (issueStatus.equals("Open") || issueStatus.equals("Close")) {
      return false;
    } else {
      return true;
    }
  }

  public Executor getExecutor() {
    return Executor.newInstance().auth("6cb900e15997599fb5775140b95212f1", "");
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
