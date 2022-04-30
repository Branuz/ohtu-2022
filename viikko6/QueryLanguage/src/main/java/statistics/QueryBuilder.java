package statistics;

import statistics.matcher.All;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

public class QueryBuilder {

    Matcher matcher;

    public QueryBuilder() {
        this.matcher = new All();
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        this.matcher = new And(this.matcher, new HasAtLeast(value, category));

        return this;
    }

    public QueryBuilder hasFewerThan(int value, String text) {
        this.matcher = new And(this.matcher, new HasFewerThan(value, text));

        return this;
    }

    public QueryBuilder playsIn(String team) {
        this.matcher = new And(this.matcher, new PlaysIn(team));

        return this;
    }

    public Matcher build() {
        Matcher buildMatcher = this.matcher;
        this.matcher = new All();

        return buildMatcher;
    }

    public QueryBuilder oneOf(Matcher... matchers) {
        this.matcher = new Or(matchers);
        
        return this;
    }
}
