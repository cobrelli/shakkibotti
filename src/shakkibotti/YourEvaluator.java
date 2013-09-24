/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shakkibotti;

import position.Evaluator;
import position.Position;

/**
 *
 * @author Hannu
 */
public class YourEvaluator extends Evaluator {

    @Override
    public double eval(Position p) {

        if (p.winner != 0) {
            return p.winner;
        }

        int wBishopCount = 0;
        int bBishopCount = 0;
        int wRookCount = 0;
        int bRookCount = 0;
        int wKnightCount = 0;
        int bKnightCount = 0;
        boolean onlyBKingLeft = true;
        boolean onlyWKingLeft = true;
        double ret = 0;

        for (int x = 0; x < p.board.length; ++x) {
            for (int y = 0; y < p.board[x].length; ++y) {
                if (p.board[x][y] == Position.Empty) {

                    continue;
                }
                if (p.board[x][y] == Position.WKing) {
                    ret += 1e9;

                }
                if (p.board[x][y] == Position.WQueen) {
                    ret += 11;
                    onlyWKingLeft = false;
                }
                if (p.board[x][y] == Position.WRook) {
                    ret += 5.25;
                    wRookCount++;
                    if (wRookCount == 2) {
                        ret += 0.55;
                    }
                    onlyWKingLeft = false;
//                    for (int i = 0; i < p.board[x].length; i++) {
//                        if (p.board[x][i] == Position.WPawn) {
//                        }
//                    }
                }
                if (p.board[x][y] == Position.WBishop) {
                    ret += 3.25;
                    wBishopCount += 1;
                    if (wBishopCount == 2) {
                        ret += 0.5;
                    }
                    onlyWKingLeft = false;
                }
                if (p.board[x][y] == Position.WKnight) {
                    ret += 3;
                    onlyWKingLeft = false;
                    wKnightCount++;
                    if (wKnightCount == 2) {
                        ret += 0.3;
                    }
                }
                if (p.board[x][y] == Position.WPawn) {
                    ret += 1;
                    onlyWKingLeft = false;
                    if (x == 0 || y == p.board.length) {
                        ret -= 0.1;
                    }
                    if (y != p.board[x].length) {
                        if (p.board[x][y + 1] == Position.WPawn) {
                            ret -= 0.1;
                        }
                    }
                    //
                    if (x == 0) {
                        if (y != 0 && y != p.board[x].length && y != 0) {
                            if (p.board[x + 1][y] != Position.WPawn || p.board[x + 1][y + 1] != Position.WPawn || p.board[x + 1][y - 1] != Position.WPawn) {
                                ret -= 0.25;
                            }
                        }

                    } else if (x == p.board.length) {
                        if (y != 0 && y != p.board[x].length && y != 0) {
                            if (p.board[x - 1][y] != Position.WPawn || p.board[x - 1][y + 1] != Position.WPawn || p.board[x - 1][y - 1] != Position.WPawn) {
                                ret -= 0.25;
                            }
                        }
                    } else {
                        if (y != 0 && y != p.board[x].length && y != 0) {
                            if (p.board[x - 1][y] != Position.WPawn || p.board[x - 1][y + 1] != Position.WPawn || p.board[x - 1][y - 1] != Position.WPawn || p.board[x + 1][y] != Position.WPawn || p.board[x + 1][y + 1] != Position.WPawn || p.board[x + 1][y - 1] != Position.WPawn) {
                                ret -= 0.25;
                            }
                        }
                    }
//                    if (x == p.board.length) {
//                        if (p.board[x - 1][y] != Position.WPawn || p.board[x - 2][y] != Position.WPawn) {
//                            ret -= 0.15;
//                        }
//                    } else if (x == 0) {
//                        if (p.board[x + 1][y] != Position.WPawn || p.board[x + 2][y] != Position.WPawn) {
//                            ret -= 0.15;
//                        }
//                    } else {
//                        if (p.board[x + 1][y] != Position.WPawn || p.board[x + 2][y] != Position.WPawn
//                                || p.board[x - 1][y] != Position.WPawn || p.board[x - 2][y] != Position.WPawn) {
//                            ret -= 0.15;
//                        }
//                    }

                }
                if (p.board[x][y] == Position.BKing) {
                    ret -= 1e9;
                }
                if (p.board[x][y] == Position.BQueen) {
                    ret -= 11;
                    onlyBKingLeft = false;
                }
                if (p.board[x][y] == Position.BRook) {
                    ret -= 5.25;
                    onlyBKingLeft = false;
                    bRookCount++;
                    if (bRookCount == 2) {
                        ret -= 0.55;
                    }
                }
                if (p.board[x][y] == Position.BBishop) {
                    ret -= 3.25;
                    onlyBKingLeft = false;
                    bBishopCount += 1;
                    if (bBishopCount == 2) {
                        ret -= 0.5;
                    }
                }
                if (p.board[x][y] == Position.BKnight) {
                    ret -= 3;
                    onlyBKingLeft = false;
                    bKnightCount++;
                    if (bKnightCount == 2) {
                        ret -= 0.3;
                    }
                }
                if (p.board[x][y] == Position.BPawn) {
                    ret -= 1;
                    onlyBKingLeft = false;
                    if (x == 0 || y == p.board.length) {
                        ret += 0.1;
                    }
                    if (y != 0) {
                        if (p.board[x][y - 1] == Position.BPawn) {
                            ret += 0.1;
                        }
                    }
                    //
                    if (x == 0) {
                        if (y != 0 && y != p.board[x].length && y != 0) {
                            if (p.board[x + 1][y] != Position.BPawn || p.board[x + 1][y + 1] != Position.BPawn || p.board[x + 1][y - 1] != Position.BPawn) {
                                ret += 0.25;
                            }
                        }

                    } else if (x == p.board.length) {
                        if (y != 0 && y != p.board[x].length && y != 0) {
                            if (p.board[x - 1][y] != Position.BPawn || p.board[x - 1][y + 1] != Position.BPawn || p.board[x - 1][y - 1] != Position.BPawn) {
                                ret += 0.25;
                            }
                        }
                    } else {
                        if (y != 0 && y != p.board[x].length && y != 0) {
                            if (p.board[x - 1][y] != Position.BPawn || p.board[x - 1][y + 1] != Position.BPawn || p.board[x - 1][y - 1] != Position.BPawn || p.board[x + 1][y] != Position.BPawn || p.board[x + 1][y + 1] != Position.BPawn || p.board[x + 1][y - 1] != Position.BPawn) {
                                ret += 0.25;
                            }
                        }
                    }
//                    if (x == p.board.length) {
//                        if (p.board[x - 1][y] != Position.BPawn || p.board[x - 2][y] != Position.BPawn) {
//                            ret += 0.15;
//                        }
//                    } else if (x == 0) {
//                        if (p.board[x + 1][y] != Position.BPawn || p.board[x + 2][y] != Position.BPawn) {
//                            ret += 0.15;
//                        }
//                    } else {
//                        if (p.board[x + 1][y] != Position.BPawn || p.board[x + 2][y] != Position.BPawn
//                                || p.board[x - 1][y] != Position.BPawn || p.board[x - 2][y] != Position.BPawn) {
//                            ret += 0.15;
//                        }
//                    }
                }
            }
        }

//        if (p.winner == -1) {
////            ret -= 100000;
//        } else if (p.winner == 1) {
//            ret += 100000;
//        }
        if (onlyBKingLeft) {
            ret += 100;
        }
        if (onlyWKingLeft) {
            ret -= 100;
        }
        return ret;
    }
}
