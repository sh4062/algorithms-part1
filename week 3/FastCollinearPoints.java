import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private int numberOfSegments;
    private ArrayList<LineSegment> segment = new ArrayList<LineSegment>();
    private Point[] points;

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new java.lang.NullPointerException();
        this.points = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            this.points[i] = points[i];
        }
        numberOfSegments = 0;
        Arrays.sort(this.points);
        // remove repeat points
        for (int i = 0; i < this.points.length - 1; i++) {
            if (this.points[i].compareTo(this.points[i + 1]) == 0) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        Point[] temp = new Point[this.points.length];
        Point[] copy = new Point[this.points.length];
        ArrayList<ArrayList<Point>> end_sets = new ArrayList<>();
        // use a copy to sort
        for (int i = 0; i < this.points.length; i++) {
            temp[i] = copy[i] = this.points[i];
            end_sets.add(new ArrayList<Point>());
        }

        Arrays.sort(copy);
        
        // to sort by slope
        for (int i = 0; i < copy.length - 1; i++) {
            Arrays.sort(temp, copy[i].slopeOrder());
            // find same slope copy then save them as segment in segment
            // ArrayList
            int count = 1;
            double slope0 = copy[i].slopeTo(temp[0]);
            ArrayList<Point> set = new ArrayList<>();
            for (int j = 0; j < temp.length; j++) {

                // record max point
                double slope1 = copy[i].slopeTo(temp[j]);
                if (slope1 == slope0) {
                    set.add(temp[j]);
                    count++;
                    if (count > 2 && j == temp.length - 1) {
                        set.add(copy[i]);
                        Collections.sort(set);
                        // System.out.println(set);
                        if (set.get(0).compareTo(copy[i]) < 0) {
                            
                        } else {
                            // no key, no set
                            segment.add(new LineSegment(set.get(0), set.get(set.size() - 1)));
                            numberOfSegments++;
                        }                        
                        count = 1;
                    }
                } else {
                    if (count > 2) {
                        set.add(copy[i]);
                        Collections.sort(set);
                        // System.out.println(set);
                        
                        if (set.get(0).compareTo(copy[i]) < 0) {
                            
                        } else {
                            // no key, no set
                            segment.add(new LineSegment(set.get(0), set.get(set.size() - 1)));
                            numberOfSegments++;
                        }
                    }
                    set = new ArrayList<>();
                    set.add(temp[j]);
                    count = 1;
                }
                slope0 = slope1;
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return numberOfSegments;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] results = new LineSegment[numberOfSegments];
        for (int i = 0; i < numberOfSegments; i++) {
            results[i] = segment.get(i);
        }
        return results;
    }
   public static void main(String[] args) {
       // TODO Auto-generated method stub

       // read the n points from a file
       In in = new In("C:/Users/123/Desktop/collinear/collinear/input8.txt");
       int n = in.readInt();
       Point[] points = new Point[n];
       for (int i = 0; i < n; i++) {
           int x = in.readInt();
           int y = in.readInt();
           points[i] = new Point(x, y);
       }

       // draw the points
       StdDraw.setPenRadius(0.01);
       StdDraw.enableDoubleBuffering();
       StdDraw.setXscale(0, 32768);
       StdDraw.setYscale(0, 32768);


       // print and draw the line segments
       FastCollinearPoints collinear = new FastCollinearPoints(points);
       System.out.println(collinear.numberOfSegments);
       for (LineSegment segment : collinear.segments()) {
           StdOut.println(segment);
           segment.draw();
       }
       StdDraw.show();
       

   }   }
