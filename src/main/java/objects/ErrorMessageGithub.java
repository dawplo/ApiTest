package objects;

import java.util.List;

public class ErrorMessageGithub {

    private List<ErrorsGithub> searchGitExample;

    private List<ErrorsGithub> incomplete_results;

    private List<ErrorMessageGithub> items;

    private List<ErrorMessageGithub> total_count;

    public void setTotal_count(List<ErrorMessageGithub> total_count) {
        this.total_count = total_count;
    }

    public List<ErrorMessageGithub> getTotal_count() {
        return total_count;

    }

    public void setItems(List<ErrorMessageGithub> items) {
        this.items = items;
    }

    public List<ErrorMessageGithub> getItems() {

        return items;
    }

    public void setSearchGitExample(List<ErrorsGithub> searchGitExample) {
        this.searchGitExample = searchGitExample;
    }

    public void setIncomplete_results(List<ErrorsGithub> incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<ErrorsGithub> getSearchGitExample() {
        return searchGitExample;
    }

    public List<ErrorsGithub> getIncomplete_results() {
        return incomplete_results;
    }
}

